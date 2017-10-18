package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.DocumentoServicio;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.persistence.intf.DocumentoServicioFacade;
import org.ibit.rol.sac.persistence.intf.DocumentoServicioFacadeHome;
import org.ibit.rol.sac.persistence.util.DocumentoServicioFacadeUtil;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/**
 * Business delegate para manipular Documento Servicio.
 */
public class DocumentoServicioDelegate implements StatelessDelegate
{
	/* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	private static final long serialVersionUID = 1L;

	/**
	 * Obtener documento normativa.
	 * @param id
	 * @return
	 * @throws DelegateException
	 */
	public DocumentoServicio obtenerDocumentoServicio(Long id) throws DelegateException {
        try {
           return getFacade().obtenerDocumentoServicio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	/**
	 * Borrar documento. 
	 * @param id
	 * @throws DelegateException
	 */
	public void borrarDocumentoServicio(Long id) throws DelegateException {
        try {
            getFacade().borrarDocumentoServicio(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	

	/**
	 * Para obtener el id de todos los documentos de servicios.
	 * @param idServicio
	 * @return
	 * @throws DelegateException
	 */
	public List<Long> obtenerDocumentosServiciosSolr(Long idServicio) throws DelegateException {
		 try {
	            return getFacade().obtenerDocumentosServiciosSolr(idServicio);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	}
	
	/**
	 * Grabar documento. 
	 * @param doc
	 * @param idNormativa
	 * @return
	 * @throws DelegateException
	 */
	public Long grabarDocument(DocumentoServicio doc, Long idNormativa) throws DelegateException {
		try {
	        return getFacade().grabarDocument(doc, idNormativa);
	     } catch (RemoteException e) {
	         throw new DelegateException(e);
	     }
    }
	
	/**
	 * Indexar documento servicio para solr. 
 	 * 
	 * @param solrIndexer
	 * @param idDocumentoServicio
	 * @param categoria
	 * @return
	 * @throws DelegateException
	 */
	public SolrPendienteResultado indexarSolrServicioDoc(SolrIndexer solrIndexer, Long idDocumentoServicio, EnumCategoria categoria) throws DelegateException {
		try {
	        return getFacade().indexarSolrServicioDoc(solrIndexer, idDocumentoServicio, categoria);
	     } catch (RemoteException e) {
	         throw new DelegateException(e);
	     }
	}
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private DocumentoServicioFacade getFacade() throws RemoteException {
        return (DocumentoServicioFacade) facadeHandle.getEJBObject();
    }
    
    
    
    protected DocumentoServicioDelegate() throws DelegateException {
        try {
            DocumentoServicioFacadeHome home = DocumentoServicioFacadeUtil.getHome();
            DocumentoServicioFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
}
