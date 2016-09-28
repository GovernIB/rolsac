package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Idioma;
import org.ibit.rol.sac.model.SolrPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.persistence.intf.SolrPendienteFacade;
import org.ibit.rol.sac.persistence.intf.SolrPendienteFacadeHome;
import org.ibit.rol.sac.persistence.util.SolrPendienteFacadeUtil;
import org.quartz.SchedulerException;

import es.caib.rolsac.utils.ResultadoBusqueda;

/**
 * Business delegate pera consultar idiomas.
 */
public class SolrPendienteDelegateImpl extends SolrPendienteDelegate implements  StatelessDelegate, SolrPendienteDelegateI {

	 
    private long timeLen = 0L;

    // Cache de lenguaje por defecto
    private String porDefecto = null;
    private long timeDef = 0L;

    private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    } 
    
    
    private Handle facadeHandle;

    private SolrPendienteFacade getFacade() throws RemoteException {
        return (SolrPendienteFacade) facadeHandle.getEJBObject();
    }

    protected SolrPendienteDelegateImpl() throws DelegateException {
        try {
        	SolrPendienteFacadeHome home = SolrPendienteFacadeUtil.getHome();
        	SolrPendienteFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public SolrPendienteJob crearSorlPendienteJob() throws DelegateException {
    	try {
            return getFacade().crearSorlPendienteJob();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    public void cerrarSorlPendienteJob(SolrPendienteJob solrpendienteJob) throws DelegateException {
    	try {
             getFacade().cerrarSorlPendienteJob(solrpendienteJob);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#getPendientes()
	 */
    public List<SolrPendiente> getPendientes() throws DelegateException {

        try {
            return getFacade().getPendientes();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
   
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#borrarCaducadas()
	 */
    public Boolean borrarCaducadas() throws DelegateException {

        try {
            return getFacade().borrarCaducadas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
   
    /* (non-Javadoc)
 	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#grabarSolrPendiente()
 	 */
     public Boolean grabarSolrPendiente(String tipo, Long idElemento, Long accion) throws DelegateException {

         try {
             return getFacade().grabarSolrPendiente(tipo, idElemento, accion);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
     }
    
     /* (non-Javadoc)
  	 * @see org.ibit.rol.sac.persistence.delegate.SolrPendienteDelegateI#getPendientes()
  	 */
      public ResultadoBusqueda getPendientes(int pagina, int resultados) throws DelegateException {

          try {
              return getFacade().getPendientes(pagina, resultados);
          } catch (RemoteException e) {
              throw new DelegateException(e);
          }
      }
      
      public List<SolrPendienteJob> getListJobs(int cuantos) throws DelegateException{
    	  try {
              return getFacade().getListJobs(cuantos);
          } catch (RemoteException e) {
              throw new DelegateException(e);
          }
  	 }
      
      
      public boolean checkJobsActivos() throws DelegateException {
    	  try {
              return getFacade().checkJobsActivos();
          } catch (RemoteException e) {
              throw new DelegateException(e);
          }
      }

      public Boolean cerrarJobs() throws DelegateException {
    	  try {
              return getFacade().cerrarJobs();
          } catch (RemoteException e) {
              throw new DelegateException(e);
          }
      }
    
}
