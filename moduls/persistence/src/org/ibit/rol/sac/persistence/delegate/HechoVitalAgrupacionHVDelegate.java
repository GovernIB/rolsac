package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.HechoVitalAgrupacionHV;
import org.ibit.rol.sac.persistence.intf.HechoVitalAgrupacionHVFacade;
import org.ibit.rol.sac.persistence.intf.HechoVitalAgrupacionHVFacadeHome;
import org.ibit.rol.sac.persistence.util.HechoVitalAgrupacionHVFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Business delegate para manipular HechoVitalAgrupacionHV.
 */
public class HechoVitalAgrupacionHVDelegate implements StatelessDelegate {
   /* ========================================================= */
   /* ======================== MÉTODOS DE NEGOCIO ============= */
   /* ========================================================= */

   public Long grabarHechoVitalAgrupacionHV(HechoVitalAgrupacionHV hechovp, Long hecho_id, Long agrupacion_id) throws DelegateException {
       try {
           return getFacade().grabarHechoVitalAgrupacionHV(hechovp, hecho_id, agrupacion_id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

   public HechoVitalAgrupacionHV obtenerHechoVitalAgrupacionHV(Long id) throws DelegateException {
       try {
           return getFacade().obtenerHechoVitalAgrupacionHV(id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

    public void subirOrden(Long id) throws DelegateException {
        try {
            getFacade().subirOrden(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

   public void borrarHechoVitalAgrupacionHV(Long id) throws DelegateException {
       try {
           getFacade().borrarHechoVitalAgrupacionHV(id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

   /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private HechoVitalAgrupacionHVFacade getFacade() throws RemoteException {
        return (HechoVitalAgrupacionHVFacade) facadeHandle.getEJBObject();
    }

    protected HechoVitalAgrupacionHVDelegate() throws DelegateException {
        try {
            HechoVitalAgrupacionHVFacadeHome home = HechoVitalAgrupacionHVFacadeUtil.getHome();
            HechoVitalAgrupacionHVFacade remote = home.create();
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
