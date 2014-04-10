package org.ibit.rol.sac.persistence.ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.EJBException;

import net.sf.hibernate.Hibernate;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Session;

import org.ibit.rol.sac.model.Documento;
import org.ibit.rol.sac.model.DocumentoResumen;
import org.ibit.rol.sac.model.Ficha;
import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.TraduccionDocumentoResumen;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;
import org.ibit.rol.sac.persistence.delegate.FichaDelegate;
import org.ibit.rol.sac.persistence.delegate.ProcedimientoDelegate;
import org.ibit.rol.sac.persistence.intf.AccesoManagerLocal;
import org.ibit.rol.sac.persistence.ws.Actualizador;

/**
 * SessionBean para mantener y consultar DocumentoResumen.
 *
 * @ejb.bean
 *  name="sac/persistence/DocumentoResumenFacade"
 *  jndi-name="org.ibit.rol.sac.persistence.DocumentoResumenFacade"
 *  type="Stateless"
 *  view-type="remote"
 *  transaction-type="Container"
 *
 * @jboss.call-by-value call-by-value="true"
 *
 * @ejb.transaction type="Required"
 */

@SuppressWarnings("deprecation")
public abstract class DocumentoResumenFacadeEJB extends HibernateEJB {

	private static final long serialVersionUID = 1L;

	/**
     * Obtiene refer�ncia al ejb de control de Acceso.
     * @ejb.ejb-ref ejb-name="sac/persistence/AccesoManager"
     */
    protected abstract AccesoManagerLocal getAccesoManager();

    /**
     * @ejb.create-method
     * @ejb.permission unchecked="true"
     */
    public void ejbCreate() throws CreateException {
        super.ejbCreate();
    }
    
    ProcedimientoDelegate procDel;
    FichaDelegate fichDel;
    
    public ProcedimientoDelegate getProcDel() {
		return procDel;
	}

	public void setProcDel(ProcedimientoDelegate procDel) {
		this.procDel = procDel;
	}

	public FichaDelegate getFichDel() {
		return fichDel;
	}

	public void setFichDel(FichaDelegate fichDel) {
		this.fichDel = fichDel;
	}
	
	/**
     * Obtiene un DocumentoResumen.
     * @ejb.interface-method
     * @ejb.permission unchecked="true"
     */
	public DocumentoResumen obtenerDocumentoResumen(Long id)
	{
		Session session = getSession();
		try {
            DocumentoResumen doc =  (DocumentoResumen) session.load(DocumentoResumen.class, id);
            Hibernate.initialize(doc.getArchivoResumen());
            for (Iterator iterator = doc.getLangs().iterator(); iterator.hasNext();) {
                String lang = (String) iterator.next();
                TraduccionDocumentoResumen traduccionResumen = (TraduccionDocumentoResumen) doc.getTraduccion(lang);
                if (traduccionResumen != null) {
                    Hibernate.initialize(traduccionResumen.getArchivoResumen());
                }
            }
            
            return doc;
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }

    /**
     * Borrar un documento
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
    public void borrarDocumento(Long id)
    {
        Session session = getSession();
        try {
            if (!getAccesoManager().tieneAccesoDocumento(id)) {
                throw new SecurityException("No tiene acceso al documento");
            }
        	Ficha ficha = null;
        	ProcedimientoLocal procedimiento = null;
            Documento documento = (Documento) session.load(Documento.class, id);
            if (documento.getFicha() != null) {
            	ficha = documento.getFicha();
                documento.getFicha().removeDocumento(documento);
            }
            if (documento.getProcedimiento() != null) {
            	procedimiento = documento.getProcedimiento();
                documento.getProcedimiento().removeDocumento(documento);
            }
            
            session.delete(documento);
            session.flush();
            if (ficha != null) {
                FichaDelegate ficdel = DelegateUtil.getFichaDelegate();
                try {
                	ficdel.indexBorraFicha(ficha.getId());
                	ficdel.indexInsertaFicha(ficha,null);
                } catch (DelegateException e) {
                    log.error("Error indexando ficha", e);
                }
            }
            if (procedimiento != null) {
        		ProcedimientoDelegate pldel = DelegateUtil.getProcedimientoDelegate();
                try {
                	pldel.indexBorraProcedimiento(procedimiento);
                	pldel.indexInsertaProcedimiento(procedimiento,null);
                    Actualizador.borrar(documento, procedimiento.getId());
                } catch (DelegateException e) {
                    log.error("Error indexando procedimiento", e);
                }
            }
        } catch (HibernateException he) {
            throw new EJBException(he);
        } finally {
            close(session);
        }
    }
    	
	/**
     * 
     * Actualiza los ordenes de los documentos de una secci�n de una Ficha
	 * 
     * FIXME enric@dgtic: este metodo lo pondria en procedimientoFacadeEJB
     * 
     * @param Map <String,String[]>
     * e.g. key= orden_doc396279
     * 	   value={"1"}
     * @param documentosABorrar lista de documentos que se borrarán previamente a la ordenación.
     * @ejb.interface-method
     * @ejb.permission role-name="${role.system},${role.admin},${role.super},${role.oper}"
     */
	public void actualizarOrdenDocs(Map map, List documentosABorrar) {
		
		Session session = getSession();
		
		try {
			
			// Primaremente borramos los documentos especificados.
			for (Documento d : (List<Documento>)documentosABorrar) {
				if (d != null)
					borrarDocumento(d.getId());
			}
			
			Long id;
			int valor_orden = 0;
			List doc_orden = new ArrayList();

			Iterator it = map.entrySet().iterator();
			
			while (it.hasNext()) {
				
				Map.Entry e = (Map.Entry) it.next();

				String paramName = e.getKey().toString();
				
				if (paramName.startsWith("orden_doc")) {
					
					id = Long.valueOf(paramName.substring(9)).longValue();
					String[] parametros = (String[]) e.getValue();
					valor_orden = Integer.parseInt(parametros[0]);

					if (!getAccesoManager().tieneAccesoDocumento(id)) {
						throw new SecurityException("No tiene acceso al documento");
					}
					
					DocumentoResumen documentoResumen = (DocumentoResumen) session.load(DocumentoResumen.class, id);
					documentoResumen.setOrden(valor_orden);
					doc_orden.add(documentoResumen);
					
				}
				
			}
			
			// FIXME amartin: cuando se guardan documentos asociados a procedimientos, tras este
			// flush sólo se realizan updates la primera vez. El resto de veces que los documentos
			// asociados al procedimiento se modifican (vía AJAX o vía "normal"), este flush no produce updates.
			// Desconozco el motivo.
			session.flush();

			Collections.sort(doc_orden, new DocsFichaComparator());

			Long contador = Long.parseLong("1");

			Iterator itdoc = doc_orden.iterator();
			DocumentoResumen doc = null;
			
			while (itdoc.hasNext()) {
				doc = (DocumentoResumen)itdoc.next();
				doc.setOrden(contador);
				contador += 1;
			}
			
			session.flush();

		} catch (HibernateException he) {
			
			throw new EJBException(he);
			
		} finally {
			
			close(session);
			
		}
		
	}
	
    class DocsFichaComparator implements Comparator {
		public int compare(Object o1, Object o2) { 
			Long x1 = new Long (((DocumentoResumen)o1).getOrden());
			Long x2 = new Long (((DocumentoResumen)o2).getOrden());
			return x1.compareTo( x2 ); 
		}
	}
    
}
