package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.IconoFamilia;
import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.persistence.intf.IconoFamiliaFacade;
import org.ibit.rol.sac.persistence.intf.IconoFamiliaFacadeHome;
import org.ibit.rol.sac.persistence.util.IconoFamiliaFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.Collection;

/**
 * Business delegate para manipular Iconos familias.
 */
public class IconoFamiliaDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarIconoFamilia(IconoFamilia icono, Long familia_id, Long perfil_id) throws DelegateException {
        try {
            return getFacade().grabarIconoFamilia(icono, familia_id, perfil_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public IconoFamilia obtenerIconoFamilia(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIconoFamilia(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public IconoFamilia obtenerIconoFamilia(Long id_perfil,Long id_fam) throws DelegateException {
        try {
            return getFacade().obtenerIconoFamilia(id_perfil,id_fam);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

     public Archivo obtenerIcono(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIcono(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

     public void borrarIconoFamilia(Long id) throws DelegateException {
         try {
             getFacade().borrarIconoFamilia(id);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
     }
     
     public void borrarIconosFamilia(Collection<Long> iconosABorrar) throws DelegateException {
         try {
             getFacade().borrarIconosFamilia(iconosABorrar);
         } catch (RemoteException e) {
             throw new DelegateException(e);
         }
     }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private IconoFamiliaFacade getFacade() throws RemoteException {
        return (IconoFamiliaFacade) facadeHandle.getEJBObject();
    }

    protected IconoFamiliaDelegate() throws DelegateException {
        try {
            IconoFamiliaFacadeHome home = IconoFamiliaFacadeUtil.getHome();
            IconoFamiliaFacade remote = home.create();
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
