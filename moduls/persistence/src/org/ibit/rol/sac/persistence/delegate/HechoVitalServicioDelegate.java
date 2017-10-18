package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.HechoVitalServicio;
import org.ibit.rol.sac.persistence.intf.HechoVitalServicioFacade;
import org.ibit.rol.sac.persistence.intf.HechoVitalServicioFacadeHome;
import org.ibit.rol.sac.persistence.util.HechoVitalServicioFacadeUtil;

/**
 * Business delegate para manipular HechoVitalServicio.
 */
public class HechoVitalServicioDelegate implements StatelessDelegate
{
	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	private static final long serialVersionUID = 1L;

	public void grabarHechoVitalServicios(Collection<HechoVitalServicio> hvpsAGrabar) throws DelegateException {
		try {
			getFacade().grabarHechoVitalServicios(hvpsAGrabar);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public void grabarHechoVitalServicios(Collection<HechoVitalServicio> hvpsAGrabar, 
    		Collection<Long> hvProcIds) throws DelegateException {
		try {
			getFacade().grabarHechoVitalServicios(hvpsAGrabar, hvProcIds);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
    
    public void borrarHechoVitalServicios(Collection<Long> hvpsABorrar) throws DelegateException {
        try {
            getFacade().borrarHechoVitalServicios(hvpsABorrar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private HechoVitalServicioFacade getFacade() throws RemoteException {
        return (HechoVitalServicioFacade) facadeHandle.getEJBObject();
    }
    
    protected HechoVitalServicioDelegate() throws DelegateException {
        try {
            HechoVitalServicioFacadeHome home = HechoVitalServicioFacadeUtil.getHome();
            HechoVitalServicioFacade remote = home.create();
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
