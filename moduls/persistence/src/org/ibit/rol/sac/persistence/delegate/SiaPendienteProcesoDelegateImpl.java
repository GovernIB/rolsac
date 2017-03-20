package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.ProcedimientoLocal;
import org.ibit.rol.sac.model.SiaJob;
import org.ibit.rol.sac.model.SiaPendiente;
import org.ibit.rol.sac.model.SolrPendienteJob;
import org.ibit.rol.sac.model.ws.SiaResultado;
import org.ibit.rol.sac.persistence.util.FiltroSia;
import org.ibit.rol.sac.persistence.intf.SiaPendienteProcesoFacade;
import org.ibit.rol.sac.persistence.intf.SiaPendienteProcesoFacadeHome;
import org.ibit.rol.sac.persistence.util.SiaPendienteProcesoFacadeUtil;

/**
 * Business delegate pera consultar idiomas.
 */
public class SiaPendienteProcesoDelegateImpl extends SiaPendienteProcesoDelegate implements  StatelessDelegate, SiaPendienteProcesoDelegateI {

	
    private long timeLen = 0L;

    // Cache de lenguaje por defecto
    private String porDefecto = null;
    private long timeDef = 0L;

    private static long maxtime = 60000L; // 60 segundos

    private boolean timeout(long time) {

        return ((System.currentTimeMillis() - time) > maxtime);
    } 
    
    
    private Handle facadeHandle;

    private SiaPendienteProcesoFacade getFacade() throws RemoteException {
        return (SiaPendienteProcesoFacade) facadeHandle.getEJBObject();
    }

    protected SiaPendienteProcesoDelegateImpl() throws DelegateException {
        try {
        	SiaPendienteProcesoFacadeHome home = SiaPendienteProcesoFacadeUtil.getHome();
        	SiaPendienteProcesoFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

 
	@Override
	public List<SiaPendiente> getSiaPendientesEnviar() throws DelegateException{
	
	   try {
			return getFacade().getSiaPendientesEnviar();
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}

	@Override
	public List<SiaPendiente> getSiaPendientes(FiltroSia filtro) throws DelegateException{
		 try {
				return getFacade().getSiaPendientes(filtro);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
	}

	@Override
	public List<SiaJob> getSiaProceso(FiltroSia filtro) throws DelegateException{
		 try {
				return getFacade().getSiaProceso(filtro);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
	}

	@Override
	public SiaPendiente generarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException{
		 try {
				return getFacade().generarSiaPendiente(siaPendiente);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
	}

	@Override
	public SiaPendiente actualizarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException{
		 try {
				return getFacade().actualizarSiaPendiente(siaPendiente);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
	}

	@Override
	public void borrarSiaPendiente(SiaPendiente siaPendiente) throws DelegateException{
		 try {
				getFacade().borrarSiaPendiente(siaPendiente);
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
		
	}
	@Override
	public boolean checkJobsActivos() throws DelegateException{
		 try {
				return getFacade().checkJobsActivos();
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
		
	}
	@Override
	public Boolean cerrarJobs() throws DelegateException{
		 try {
				return getFacade().cerrarJobs();
	       } catch (RemoteException e) {
	           throw new DelegateException(e);
	       }
		
	}
	@Override
	public SiaJob crearSiaJob(String tipo)  throws DelegateException {
		try {
			return getFacade().crearSiaJob(tipo);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
	
	@Override
	public void actualizarSiaJob(SiaJob siaJob) throws DelegateException{
		try {
			getFacade().actualizarSiaJob(siaJob);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
	
	
	
	@Override
	public void cerrarSiaJob(Long idSiaJob) throws DelegateException{
		try {
			getFacade().cerrarSiaJob(idSiaJob);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
	
	@Override
	public SiaResultado enviarProcedimiento(ProcedimientoLocal proc)  throws DelegateException{
		try {
			return getFacade().enviarProcedimiento(proc);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
	
	
	@Override
	public SiaResultado borradoProcedimiento(Long idProc, String idSIA)  throws DelegateException{
		try {
			return getFacade().borradoProcedimiento(idProc, idSIA);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
	
}
