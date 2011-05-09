package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.MateriaAgrupacionM;
import org.ibit.rol.sac.persistence.intf.MateriaAgrupacionMFacade;
import org.ibit.rol.sac.persistence.intf.MateriaAgrupacionMFacadeHome;
import org.ibit.rol.sac.persistence.util.MateriaAgrupacionMFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;

/**
 * Business delegate para manipular MateriaAgrupacionM.
 */
public class MateriaAgrupacionMDelegate implements StatelessDelegate {
   /* ========================================================= */
   /* ======================== MÉTODOS DE NEGOCIO ============= */
   /* ========================================================= */

   public Long grabarMateriaAgrupacionM(MateriaAgrupacionM materiap, Long materia_id, Long agrupacion_id) throws DelegateException {
       try {
           return getFacade().grabarMateriaAgrupacionM(materiap, materia_id, agrupacion_id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

   public MateriaAgrupacionM obtenerMateriaAgrupacionM(Long id) throws DelegateException {
       try {
           return getFacade().obtenerMateriaAgrupacionM(id);
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

   public void borrarMateriaAgrupacionM(Long id) throws DelegateException {
       try {
           getFacade().borrarMateriaAgrupacionM(id);
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
   }

   /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private MateriaAgrupacionMFacade getFacade() throws RemoteException {
        return (MateriaAgrupacionMFacade) facadeHandle.getEJBObject();
    }

    protected MateriaAgrupacionMDelegate() throws DelegateException {
        try {
        	MateriaAgrupacionMFacadeHome home = MateriaAgrupacionMFacadeUtil.getHome();
        	MateriaAgrupacionMFacade remote = home.create();
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
