package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.TipoAfectacion;
import org.ibit.rol.sac.persistence.intf.TipoAfectacionFacade;
import org.ibit.rol.sac.persistence.intf.TipoAfectacionFacadeHome;
import org.ibit.rol.sac.persistence.util.TipoAfectacionFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular tipos afectacion.
 */
public class TipoAfectacionDelegate implements StatelessDelegate{

     /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarTipoAfectacion(TipoAfectacion tipoAfec) throws DelegateException {
        try {
            return getFacade().grabarTipoAfectacion(tipoAfec);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarTiposAfectaciones() throws DelegateException {
        try {
            return getFacade().listarTiposAfectaciones();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public TipoAfectacion obtenerTipoAfectacion(Long id) throws DelegateException {
        try {
            return getFacade().obtenerTipoAfectacion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public boolean tieneAfectaciones(Long id) throws DelegateException {
        try {
            return getFacade().tieneAfectaciones(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarTipoAfectacion(Long id) throws DelegateException {
        try {
            getFacade().borrarTipoAfectacion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private TipoAfectacionFacade getFacade() throws RemoteException {
        return (TipoAfectacionFacade) facadeHandle.getEJBObject();
    }

    protected TipoAfectacionDelegate() throws DelegateException {
        try {
            TipoAfectacionFacadeHome home = TipoAfectacionFacadeUtil.getHome();
            TipoAfectacionFacade remote = home.create();
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
