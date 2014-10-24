package org.ibit.rol.sac.persistence.ejb;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Criteria;
import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.CatalegDocuments;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * SessionBean per mantenir i consultar el Cataleg de Documents
 *
 * @ejb.bean
 *  name="sac/persistence/CatalegDocumentsFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.catalegDocumentsFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @ejb.transaction type="Required"
 */
public abstract class CatalegDocumentsFacadeEJB extends HibernateEJB
{
	private static final long serialVersionUID = -2185416511499772305L;
	
	private static final String excepcionSinDocumentoTramite = "El tipus de document está relacionat amb algun tramit";
	
	private static final String defaultLang = "ca";
	
	
	/**
	 * Obtiene referencia al ejb de control de acceso.
	 * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
	 */
	protected abstract AccesoManagerLocal getAccesoManager();
	
	
	/**
	 * @ejb.create-method
	 * @ejb.permission unchecked="true"
	 */
	public void ejbCreate() throws CreateException
	{
		super.ejbCreate();
	}
	
	
	/**
	 * Crea o actualiza un catálogo de documentos.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param documentoCatalogo	Catálogo de documentos a guardar.
	 * @return Devuelve el identificador del catálogo de documentos.
	 */
	public Long gravarDocumentCataleg(CatalegDocuments documentoCatalogo)
	{
		Session session = getSession();
		try {
			session.saveOrUpdate(documentoCatalogo);
			session.flush();
			return documentoCatalogo.getId();
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtenir un Document del Cataleg
	 * 
	 * @ejb.interface-method
	 * @ejb.permission unchecked="true"
	 * @param id	Identificador del catálogo de documentos.
	 * @return Devuelve <code>CatalegDocuments</code> solcitiado.
	 */
	public CatalegDocuments obtenirDocumentoCataleg(Long id)
	{
		Session session = getSession();
		try {
			CatalegDocuments docCataleg = (CatalegDocuments) session.load(CatalegDocuments.class, id);
			Hibernate.initialize(docCataleg.getExcepcioDocumentacio());
			return docCataleg;
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Lista todos los documentos del catálogo.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * @return Devuelve <code>List<CatalegDocuments></code> de todos los catálogos de documentos.
	 */
	public List<CatalegDocuments> listarCatalogoDocumentos()
	{
		Session session = getSession();
		try {
			Criteria criteri = session.createCriteria(CatalegDocuments.class);
			return castList(CatalegDocuments.class, criteri.list());
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Obtiene una lista del catálogo de documentos con multiidioma.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
	 * @param descripcionBusqueda	Cadena de texto que se utiliza para buscar el catálogo de documento que contenga éste valor en su descripción o nombre.
	 * @param administracionResponsable	Identidicador del responsable de administración.
	 * @param excepcionDocumento	Identificador de las causas de excepción de aportación de documentación.
	 * @param pagina	Indica el número de página actual del listado del catálogo de documentos.
	 * @param resultados	Indica el número de resultados por página.
	 * @param idioma	Indica el idioma en que se realiza la búsqueda.
	 * @return Devuelve <code>ResultadoBusqueda</code> con todos los catálogos de documentos filtrados por los parámetros de búsqueda.
	 */
	public ResultadoBusqueda cercarDocumentsCatalegAmbMultiidioma(String descripcionBusqueda, Long administracionResponsable, Long excepcionDocumento, Integer pagina, Integer resultados, String idioma)
	{
		Session session = getSession();
		try {
			StringBuilder consulta = new StringBuilder("select distinct catDoc from CatalegDocuments as catDoc, catDoc.traducciones as trad where ");
			if (idioma == null && "".equals(idioma)) {
				consulta.append("index(trad) = 'ca' ");
			} else {
				consulta.append("index(trad) = :idioma ");
			}
			
			if (descripcionBusqueda != null && !"".equals(descripcionBusqueda)) {
				consulta.append(" and ( upper( trad.nombre ) like :descripcionBusqueda  or  upper( trad.descripcion ) like :descripcionBusqueda ) ");
			}
			
			if (administracionResponsable != null) {
				consulta.append(" and catDoc.admResponsable = :administracionResponsable ");
			}
			
			if (excepcionDocumento != null) {
				consulta.append(" and catDoc.excepcioDocumentacio.id = :excepcionDocumento ");
			}
			
			Query query = session.createQuery(consulta.toString());
			if (idioma != null && !"".equals(idioma)) {
				query.setParameter("idioma", idioma);
			}
			
			if (descripcionBusqueda != null && !"".equals(descripcionBusqueda)) {
				query.setParameter("descripcionBusqueda", "%" + descripcionBusqueda.toUpperCase() + "%");
			}
			
			if (administracionResponsable != null) {
				query.setParameter("administracionResponsable", administracionResponsable);
			}
			
			if (excepcionDocumento != null) {
				query.setParameter("excepcionDocumento", excepcionDocumento);
			}
			
			return listarTablaMaestraPaginada(pagina, resultados, query.list());
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Borra un catálogo de documentos.
	 * 
	 * @ejb.interface-method
	 * @ejb.permission role-name="${role.system},${role.admin},${role.super}"
	 * @param id	Identificador del catálogo documentos a borrar.
	 */
	public void esborrarDocumentCataleg(Long id)
	{
		Session session = getSession();
		try {
			CatalegDocuments docCat = (CatalegDocuments) session.load(CatalegDocuments.class, id);
			Set docTramit = docCat.getDoctramite();
			if (!docTramit.isEmpty()) {
				throw new EJBException(excepcionSinDocumentoTramite);
			}
			session.delete(docCat);
			session.flush();
			Actualizador.borrar(docCat);
			
		} catch (HibernateException he) {
			throw new EJBException(he);
		} finally {
			close(session);
		}
	}
	
	
	/**
	 * Construeix la query de cerca segons els parametres
	 */
	private String populateQuery(Map parametros, Map traduccion, List params)
	{
		String aux = "";
		
		// Tratamiento de parametros
		for (Iterator<?> iter1 = parametros.keySet().iterator(); iter1.hasNext();) {
			String key = (String) iter1.next();
			Object value = parametros.get(key);
			if (value != null) {
				if (aux.length() > 0) aux = aux + " and ";
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " upper( catDoc." + key + " ) like ? " ;
							params.add(sValue);
						} else {
							aux = aux + " upper( catDoc." + key + " ) like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else {
					aux = aux + "catDoc." + key + " =  ? ";
					params.add(value);
				}
			}
		}
		
		// Tratamiento de traducciones
		if (!traduccion.isEmpty()) {
			if (aux.length() > 0) aux = aux + " and ";
			aux = aux + "index(trad) = '" + traduccion.get("idioma") + "'";
			traduccion.remove("idioma");
		}
		for (Iterator iter2 = traduccion.keySet().iterator(); iter2.hasNext();) {
			String key = (String) iter2.next();
			Object value = traduccion.get(key);
			if (value != null) {
				if (value instanceof String) {
					String sValue = (String) value;
					if (sValue.length() > 0) {
						if (sValue.startsWith("\"") && sValue.endsWith("\"")) {
							sValue = sValue.substring(1, (sValue.length() - 1));
							aux = aux + " and upper( trad." + key + " ) like ? ";
							params.add(sValue);
						} else {
							aux = aux + " and upper( trad." + key + " ) like ? ";
							params.add("%"+sValue+"%");
						}
					}
				} else {
					aux = aux + " and trad." + key + " =  ? ";
					params.add(value);
				}
			}
		}
		
		return aux;
	}
	
}
