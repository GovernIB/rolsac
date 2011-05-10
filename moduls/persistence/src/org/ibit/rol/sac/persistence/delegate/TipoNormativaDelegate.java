package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Tipo;
import org.ibit.rol.sac.persistence.intf.TipoNormativaFacadeHome;
import org.ibit.rol.sac.persistence.intf.TipoNormativaFacade;
import org.ibit.rol.sac.persistence.util.TipoNormativaFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular tipos normativas.
 */
public class TipoNormativaDelegate implements StatelessDelegate {
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarTipoNormativa(Tipo tiponorm) throws DelegateException {
        try {
            return getFacade().grabarTipoNormativa(tiponorm);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarTiposNormativas() throws DelegateException {
        try {
            return getFacade().listarTiposNormativas();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Tipo obtenerTipoNormativa(Long id) throws DelegateException {
        try {
            return getFacade().obtenerTipoNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public boolean tieneNormativas(Long id) throws DelegateException {
        try {
            return getFacade().tieneNormativas(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarTipoNormativa(Long id) throws DelegateException {
        try {
            getFacade().borrarTipoNormativa(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private TipoNormativaFacade getFacade() throws RemoteException {
        return (TipoNormativaFacade) facadeHandle.getEJBObject();
    }

    protected TipoNormativaDelegate() throws DelegateException {
        try {
            TipoNormativaFacadeHome home = TipoNormativaFacadeUtil.getHome();
            TipoNormativaFacade remote = home.create();
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
