package org.ibit.rol.sac.persistence.ejb;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import org.apache.commons.lang.StringUtils;
import org.ibit.rol.sac.model.DocumentoNormativa;
import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.TraduccionDocumentoNormativa;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.util.ApiRestUtils;
import org.ibit.rol.sac.persistence.util.IndexacionUtil;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;
import es.caib.solr.api.model.types.EnumCategoria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;


/**
 * SessionBean para mantener y consultar DocumentoNormativa. (DOCNOR)
 *
 * @ejb.bean
 * name="sac/persistence/DocumentoNormativaFacade"
 * jndi-name="org.ibit.rol.sac.persistence.DocumentoNormativaFacade"
 * type="Stateless"
 * view-type="remote"
 * transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class DocumentoNormativaFacadeEJB extends HibernateEJB
{
	/** Serial version UID. **/
	private static final long serialVersionUID = 3203877720788468776L;
	
	
	/**
	 * Obtiene referencia al ejb de control de Acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();
	
	/**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
	@Override
	public void ejbCreate() throws CreateException
	{
        super.ejbCreate();
    }
	
	
	
	/**
     * Borra una DocumentoNormativa.
     * @param id	Identificador de la unidad Normativa a borrar.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public void borrarDocumentoNormativa(Long id)
	{
		Session session = getSession();
		try {
			DocumentoNormativa documentoNormativa = (DocumentoNormativa) session.load(DocumentoNormativa.class, id);
			session.delete(documentoNormativa);
            session.flush();            
        } catch (HibernateException he) {
        	throw new EJBException(he);
        } finally {
        	close(session);
        }
	}
	

	/**
     * Obtener una DocumentoNormativa.
     * @param id	Identificador de la unidad Normativa a borrar.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin}"
     */
	public DocumentoNormativa obtenerDocumentoNormativa(Long id)
	{
		Session session = getSession();
		DocumentoNormativa documentoNormativa = null;
		try {
			documentoNormativa = (DocumentoNormativa) session.get(DocumentoNormativa.class, id);
			Hibernate.initialize(documentoNormativa.getTraducciones());
			if (documentoNormativa != null && documentoNormativa.getTraducciones() != null) {
				for(String idioma : documentoNormativa.getTraducciones().keySet()) {
					TraduccionDocumentoNormativa tradNor = (TraduccionDocumentoNormativa) documentoNormativa.getTraduccion(idioma);
					if (tradNor != null && tradNor.getArchivo() != null) {
						Hibernate.initialize(tradNor.getArchivo());
					}
				}
			}
			return documentoNormativa;
        } catch (HibernateException he) {
        	throw new EJBException(he);
        } finally {
        	close(session);
        }
	}
	
	
	/**
	 * Guarda un documento
	 * 
	 * @param	doc	Indica un documento asociado a un determinado trámite.
	 * 
	 * @param normativa	Identificador de un trámite.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * 
	 * @return Devuelve el identificador del documento guardado.
	 * @throws DelegateException 
	 */
	public Long grabarDocument(DocumentoNormativa doc, Long idNormativa) throws DelegateException {

		Session session = getSession();

		try {

			if ( !getAccesoManager().tieneAccesoNormativa(idNormativa) )
				throw new SecurityException("No tiene acceso a la normativa.");

			Normativa normativa = null;
			boolean actualizar = false;

			if ( doc.getId() == null ) {

				normativa = cargaNormativa(session, idNormativa);
				normativa.addDocumentoNormativa(doc);
				if (doc.getNormativa() == null) {
					doc.setNormativa(normativa);
				}
				if (doc.getOrden() == null) {
					Long orden = getOrden(normativa.getId(), session);
					doc.setOrden(orden);
				}
				session.save(doc);

			} else {
				
				//session.update(doc); 
				//session.flush();
				//session.update(doc);
				//normativa = cargaNormativa(session, idNormativa);
				//DocumentoNormativa docNuevo = normativa.addDocumentoNormativa(doc);
				//session.evict(normativa); session.evict(docNuevo);
				session.update(doc);
				
				actualizar = true;
			}

			session.flush();
			if (actualizar) {
				normativa = cargaNormativa(session, idNormativa);
			}

			log.debug("Grabar Documento: Lanzo el actualizador");
			Actualizador.actualizar(normativa, normativa.getId());
			
			IndexacionUtil.marcarIndexacionPendiente(EnumCategoria.ROLSAC_NORMATIVA, normativa.getId(), false);
			return doc.getId();

		} catch (HibernateException he) {

			throw new EJBException(he);

		} finally {

			close(session);

		}

	}
	
	/**
	 * Obtiene el orden máximo
	 * @param idNormativa
	 * @param session
	 * @return
	 */
	private Long getOrden(Long idNormativa, final Session session) {
		Long orden;
		try {
			final Query query = session.createQuery("Select max(documentoNormativa.orden) from DocumentoNormativa documentoNormativa where documentoNormativa.normativa.id = " + idNormativa);
			//Incrementa el orden en uno
			Object resultadoMax = query.uniqueResult();
			if (resultadoMax == null) {
				orden = 0l;
			} else {
				orden = Long.valueOf(resultadoMax.toString() ) + 1l;
			}
		} catch(Exception e) {
			orden = 0l;
		}
		return orden;
	}

	/**
	 * Se hace pública para que se pueda acceder desde procedimentFacadeEJB
	 * @param session
	 * @param idNormativa
	 * @return
	 * @throws HibernateException
	 */
	private Normativa cargaNormativa(Session session, Long idNormativa) throws HibernateException {

		Normativa normativa = (Normativa) session.load(Normativa.class, idNormativa);
		Hibernate.initialize(normativa.getDocumentos()); 
		if (normativa.getDocumentos() != null) {
			for(DocumentoNormativa documentoNormativa : normativa.getDocumentos()) {
				if (documentoNormativa != null) {
					for(String idioma : documentoNormativa.getTraducciones().keySet()) {
						TraduccionDocumentoNormativa tradNor = (TraduccionDocumentoNormativa) documentoNormativa.getTraduccion(idioma);
						if (tradNor != null && tradNor.getArchivo() != null) {
							Hibernate.initialize(tradNor.getArchivo());
						}
					}
				}
			}
		}

		return normativa;

	}
	
	
	
	
	/**
	 *  Metodo para consultar los documentos de una normativa. 
	 * @param filtro generico
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     * 
	 * @return
	 */
	public ResultadoBusqueda consultaDocumentosNormativas(FiltroGenerico filtro) {
		
		Session session = getSession();	
		Integer pageSize = filtro.getPageSize();
		Integer pageNumber = filtro.getPage();
		String lang = filtro.getLang();
		Long id = filtro.getId();
		Map <String,String> parametros = new HashMap<String,String>();
		
		
		String idNormativa = filtro.getValor(FiltroGenerico.FILTRO_DOC_NORMATIVA_NORMATIVA);
		
		
		
		StringBuilder select = new StringBuilder("SELECT d ");
		StringBuilder selectCount = new StringBuilder("SELECT count(d) ");
		StringBuilder from = new StringBuilder(" FROM DocumentoNormativa as d, d.traducciones as trad ") ;
		StringBuilder where =new StringBuilder(" WHERE index(trad) = :lang");
		parametros.put("lang",lang);
		StringBuilder order = new StringBuilder(filtro.getOrdenSQL("d"));			
				
		try {
				
			if(id!=null && id>0) {
				where.append(" AND d.id = :id");
				parametros.put("id", id.toString());					
			}
			
			if(idNormativa!=null && StringUtils.isNumeric(idNormativa) &&  Integer.parseInt(idNormativa)>0) {				
				from.append(", d.normativa as n ");
				where.append(" AND  n.id = :idNormativa");
				parametros.put("idNormativa", idNormativa);					
			}
				 
			return ApiRestUtils.ejecutaConsultaGenerica(session, pageSize, pageNumber, select.toString(), selectCount.toString(), from.toString(), where.toString(), order.toString(), parametros);
	
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
}
