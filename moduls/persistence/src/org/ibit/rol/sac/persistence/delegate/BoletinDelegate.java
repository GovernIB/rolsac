package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.Boletin;
import org.ibit.rol.sac.persistence.intf.BoletinFacade;
import org.ibit.rol.sac.persistence.intf.BoletinFacadeHome;
import org.ibit.rol.sac.persistence.util.BoletinFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular boletines.
 */
public class BoletinDelegate implements StatelessDelegate {
    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarBoletin(Boletin boletin) throws DelegateException {
        try {
            return getFacade().grabarBoletin(boletin);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public List listarBoletines() throws DelegateException {
        try {
            return getFacade().listarBoletines();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public Boletin obtenerBoletin(Long id) throws DelegateException {
        try {
            return getFacade().obtenerBoletin(id);
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


    public void borrarBoletin(Long id) throws DelegateException {
        try {
            getFacade().borrarBoletin(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private BoletinFacade getFacade() throws RemoteException {
        return (BoletinFacade) facadeHandle.getEJBObject();
    }

    protected BoletinDelegate() throws DelegateException {
        try {
            BoletinFacadeHome home = BoletinFacadeUtil.getHome();
            BoletinFacade remote = home.create();
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
