package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.ActivacionSuscripcion;
import org.ibit.rol.sac.persistence.intf.ActivacionSuscripcionFacade;
import org.ibit.rol.sac.persistence.intf.ActivacionSuscripcionFacadeHome;
import org.ibit.rol.sac.persistence.util.ActivacionSuscripcionFacadeUtil;


public class ActivacionSuscripcionDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public ActivacionSuscripcion obtener(String email, String idImagen) throws DelegateException {
        try {
        	return getFacade().obtener(email,idImagen);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }	
	
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private ActivacionSuscripcionFacade getFacade() throws RemoteException {
        return (ActivacionSuscripcionFacade) facadeHandle.getEJBObject();
    }

    protected ActivacionSuscripcionDelegate() throws DelegateException {
        try {
        	ActivacionSuscripcionFacadeHome home = ActivacionSuscripcionFacadeUtil.getHome();
        	ActivacionSuscripcionFacade remote = home.create();
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