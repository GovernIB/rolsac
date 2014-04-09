package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;
import java.util.Collection;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.HechoVitalProcedimiento;
import org.ibit.rol.sac.persistence.intf.HechoVitalProcedimientoFacade;
import org.ibit.rol.sac.persistence.intf.HechoVitalProcedimientoFacadeHome;
import org.ibit.rol.sac.persistence.util.HechoVitalProcedimientoFacadeUtil;

/**
 * Business delegate para manipular HechoVitalProcedimiento.
 */
public class HechoVitalProcedimientoDelegate implements StatelessDelegate
{
	/* ========================================================= */
	/* ======================== MÉTODOS DE NEGOCIO ============= */
	/* ========================================================= */

	private static final long serialVersionUID = 1L;

	public void grabarHechoVitalProcedimientos(Collection<HechoVitalProcedimiento> hvpsAGrabar) throws DelegateException {
		try {
			getFacade().grabarHechoVitalProcedimientos(hvpsAGrabar);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
	
	public void grabarHechoVitalProcedimientos(Collection<HechoVitalProcedimiento> hvpsAGrabar, 
    		Collection<Long> hvProcIds) throws DelegateException {
		try {
			getFacade().grabarHechoVitalProcedimientos(hvpsAGrabar, hvProcIds);
		} catch (RemoteException e) {
			throw new DelegateException(e);
		}
	}
    
    public void borrarHechoVitalProcedimientos(Collection<Long> hvpsABorrar) throws DelegateException {
        try {
            getFacade().borrarHechoVitalProcedimientos(hvpsABorrar);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */
    
    private Handle facadeHandle;
    
    private HechoVitalProcedimientoFacade getFacade() throws RemoteException {
        return (HechoVitalProcedimientoFacade) facadeHandle.getEJBObject();
    }
    
    protected HechoVitalProcedimientoDelegate() throws DelegateException {
        try {
            HechoVitalProcedimientoFacadeHome home = HechoVitalProcedimientoFacadeUtil.getHome();
            HechoVitalProcedimientoFacade remote = home.create();
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
