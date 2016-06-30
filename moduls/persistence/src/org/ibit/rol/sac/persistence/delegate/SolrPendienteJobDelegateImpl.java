package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.SolrPendienteResultado;
import org.ibit.rol.sac.persistence.intf.SolrPendienteJobFacade;
import org.ibit.rol.sac.persistence.intf.SolrPendienteJobFacadeHome;
import org.ibit.rol.sac.persistence.util.SolrPendienteJobFacadeUtil;

import es.caib.solr.api.SolrIndexer;
import es.caib.solr.api.model.types.EnumCategoria;

/**
 * Business delegate pera consultar idiomas.
 */
public class SolrPendienteJobDelegateImpl extends SolrPendienteJobDelegate implements  StatelessDelegate, SolrPendienteJobDelegateI {

	
    /** serial version uid. **/
	private static final long serialVersionUID = 1L;

	private long timeLen = 0L;

    // Cache de lenguaje por defecto
    private String porDefecto = null;
    private long timeDef = 0L;

    private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    }
    
    
    private Handle facadeHandle;

    private SolrPendienteJobFacade getFacade() throws RemoteException {
        return (SolrPendienteJobFacade) facadeHandle.getEJBObject();
    }

    protected SolrPendienteJobDelegateImpl() throws DelegateException {
        try {
        	SolrPendienteJobFacadeHome home = SolrPendienteJobFacadeUtil.getHome();
        	SolrPendienteJobFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void indexarPendiente(final SolrIndexer solrIndexer, final FichaDelegate delegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
    	try {
            getFacade().indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
    	} catch (RemoteException e) {
            throw new DelegateException(e);
        } 
	}
    public void indexarPendiente(final SolrIndexer solrIndexer, final ProcedimientoDelegate delegate, final Long idElemento, final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
    	try {
            getFacade().indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
    	} catch (RemoteException e) {
            throw new DelegateException(e);
        } 
	}
	public void indexarPendiente(final SolrIndexer solrIndexer, DocumentoDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		try {
            getFacade().indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
    	} catch (RemoteException e) {
            throw new DelegateException(e);
        } 
	}
	
	public void indexarPendiente(final SolrIndexer solrIndexer, NormativaDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		try {
            getFacade().indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
    	} catch (RemoteException e) {
            throw new DelegateException(e);
        } 
	}
	
	public void indexarPendiente(final SolrIndexer solrIndexer, TramiteDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		try {
            getFacade().indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
    	} catch (RemoteException e) {
            throw new DelegateException(e);
        } 
	}
	
	public void indexarPendiente(final SolrIndexer solrIndexer, UnidadAdministrativaDelegate delegate, final Long idElemento,  final EnumCategoria categoria, final SolrPendienteJob sorlPendienteJob) throws DelegateException {
		try {
            getFacade().indexarPendiente(solrIndexer, delegate , idElemento, categoria, sorlPendienteJob);
    	} catch (RemoteException e) {
            throw new DelegateException(e);
        } 
	}
 
	
	
	public void actualizarJob(SolrPendienteJob solrpendienteJob) throws DelegateException {
		try { 
            getFacade().actualizarJob(solrpendienteJob);
    	} catch (RemoteException e) {
            throw new DelegateException(e);
        } 
    }

	

      public SolrPendienteResultado indexarPendiente(SolrIndexer solrIndexer, final SolrPendiente solrpendiente) throws DelegateException {
    	  try {
              return getFacade().indexarPendiente(solrIndexer, solrpendiente);
          } catch (RemoteException e) {
              throw new DelegateException(e);
          }  

      }
      
      public void resolverPendiente(final SolrPendiente solrpendiente, final SolrPendienteResultado solrPendienteResultado) throws DelegateException {
    	  try {
              getFacade().resolverPendiente(solrpendiente, solrPendienteResultado);
          } catch (RemoteException e) {
              throw new DelegateException(e);
          }  

      }
    
}
