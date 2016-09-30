package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.DocumentTramit;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.model.Taxa;
import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.persistence.intf.TramiteFacade;
import org.ibit.rol.sac.persistence.intf.TramiteFacadeHome;
import org.ibit.rol.sac.persistence.util.TramiteFacadeUtil;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/*
 * ejaen@dgtic  - u92770
 * Classe desacoplada del JBOSS per permetre testing. 
 * S'ha definit una classe enlloc d'una interficie per evitar incompabilitats binaria amb les altres
 * aplicacions corporatives que esperen una classe (produeix un IncompatibleClassChangeError). 
 * 
 */

public class TramiteDelegateImpl implements StatelessDelegate, TramiteDelegateI {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#grabarTramite(org.ibit.rol.sac.model.Tramite, java.lang.Long)
	 */
    public Long grabarTramite(Tramite tramite, Long idOC) throws DelegateException {
        try {
            return getFacade().grabarTramite(tramite, idOC);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#obtenerTramite(java.lang.Long)
	 */
    public Tramite obtenerTramite(Long id) throws DelegateException {
        try {
            return getFacade().obtenerTramite(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarTramite(java.lang.Long)
	 */
    public void borrarTramite(Long id) throws DelegateException {
        try {
            getFacade().borrarTramite(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private TramiteFacade getFacade() throws RemoteException {
        return (TramiteFacade) facadeHandle.getEJBObject();
    }

    protected TramiteDelegateImpl() throws DelegateException {
        try {
            TramiteFacadeHome home = TramiteFacadeUtil.getHome();
            TramiteFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#afegirDocumentInformatiu(java.lang.Long, java.lang.Long)
	 */

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#grabarDocument(org.ibit.rol.sac.model.DocumentTramit, java.lang.Long)
	 */
	public Long grabarDocument(DocumentTramit doc, Long tid) throws DelegateException {
		 try {
	            return getFacade().grabarDocument(doc, tid);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	}
	

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#obtenirDocument(java.lang.Long)
	 */
	public DocumentTramit obtenirDocument(Long docId) throws DelegateException {
		 try {
	            return getFacade().obtenirDocument(docId);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }
	}

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarDocument(java.lang.Long)
	 */
	public void borrarDocument(Long id) throws DelegateException {
		 try {
	             getFacade().borrarDocument(id);
	        } catch (RemoteException e) {
	            throw new DelegateException(e);
	        }	
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarDocument(java.lang.Long)
	 */
	public void borrarDocumentos(Tramite tramite, List<DocumentTramit> documentos) throws DelegateException {
		try {
			getFacade().borrarDocumentos(tramite, documentos);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#actualizarOrdenDocs(java.util.Map, long)
	 */
	public void actualizarOrdenDocs(Map<String, String[]> map, long tid)
			throws DelegateException {
		 try {
			 	getFacade().actualizarOrdenDocs(map,tid);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }		
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#actualizarOrdenDocs(java.util.Map, long)
	 */
	public void actualizarOrdenTasas(Map<String, String[]> map, long tid)
			throws DelegateException {
		 try {
			 	getFacade().actualizarOrdenTasas(map,tid);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }		
	}
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#obtenirTaxa(java.lang.Long)
	 */
	public Taxa obtenirTaxa(Long docId) throws DelegateException {
		 try {
             return getFacade().obtenirTaxa(docId);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	
	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#grabarTaxa(org.ibit.rol.sac.model.Taxa, java.lang.Long)
	 */
	public Long grabarTaxa(Taxa taxa, Long tid) throws DelegateException {
		 try {
             return getFacade().grabarTaxa(taxa,tid);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	

	/* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.TramiteDelegateI#borrarTaxa(java.lang.Long)
	 */
	public void borrarTaxa(Long id) throws DelegateException {
		 try {
             getFacade().borrarTaxa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
		
	}
	
	public boolean autorizaCrearTramite(Long idProcedimiento) throws DelegateException {
		 try {
             return getFacade().autorizaCrearTramite(idProcedimiento);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public boolean autorizaModificarTramite(Long idTramite) throws DelegateException {
		 try {
             return getFacade().autorizaModificarTramite(idTramite);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
            return getFacade().indexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
	public SolrPendienteResultado indexarSolr(final SolrIndexer solrIndexer, Long idAplicacion, EnumCategoria categoria) throws DelegateException {
    	try {
    		return getFacade().indexarSolr(solrIndexer, idAplicacion, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
	public SolrPendienteResultado indexarDocSolr(final SolrIndexer solrIndexer, Long idAplicacion, EnumCategoria categoria) throws DelegateException {
    	try {
    		return getFacade().indexarDocSolr(solrIndexer, idAplicacion, categoria);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    public SolrPendienteResultado desindexarSolr(final SolrIndexer solrIndexer, final SolrPendiente solrPendiente) throws DelegateException {
    	try {
    		return getFacade().desindexarSolr(solrIndexer, solrPendiente);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	public List<Long> buscarIdsTramites() throws DelegateException {
		try {
            return getFacade().buscarIdsTramites();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
	}
	
}
