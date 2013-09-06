package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Enlace;
import org.ibit.rol.sac.persistence.intf.EnlaceFacade;
import org.ibit.rol.sac.persistence.intf.EnlaceFacadeHome;
import org.ibit.rol.sac.persistence.util.EnlaceFacadeUtil;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * Business delegate para manipular Enlaces.
 */
public class EnlaceDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarEnlace(Enlace enl, Long procedimiento_id, Long ficha_id) throws DelegateException {
        try {
            return getFacade().grabarEnlace(enl,procedimiento_id, ficha_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    /** @deprecated Se usa desde back antiguo */
    public Enlace obtenerEnlace(Long id) throws DelegateException {
        try {
            return getFacade().obtenerEnlace(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    public void borrarEnlace(Long id) throws DelegateException {
        try {
            getFacade().borrarEnlace(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    
    /** @deprecated Se usa desde back antiguo */
    public void actualizarOrdenEnlaces(Map map) throws DelegateException {
        try {
            getFacade().actualizarOrdenEnlaces(map);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private EnlaceFacade getFacade() throws RemoteException {
        return (EnlaceFacade) facadeHandle.getEJBObject();
    }

    protected EnlaceDelegate() throws DelegateException {
        try {
        	EnlaceFacadeHome home = EnlaceFacadeUtil.getHome();
        	EnlaceFacade remote = home.create();
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
