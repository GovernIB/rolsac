package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.model.UnidadMateria;
import org.ibit.rol.sac.persistence.intf.UnidadMateriaFacade;
import org.ibit.rol.sac.persistence.intf.UnidadMateriaFacadeHome;
import org.ibit.rol.sac.persistence.util.UnidadMateriaFacadeUtil;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular Unidades Materia
 */
public class UnidadMateriaDelegate implements StatelessDelegate{
      /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    public Long grabarUnidadMateria(UnidadMateria unidadMateria, Long unidad_id, Long materia_id) throws DelegateException {
        try {
            return getFacade().grabarUnidadMateria(unidadMateria, unidad_id, materia_id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public UnidadMateria obtenerUnidadMateria(Long id) throws DelegateException {
        try {
            return getFacade().obtenerUnidadMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    public void borrarUnidadMateria(Long id) throws DelegateException {
        try {
            getFacade().borrarUnidadMateria(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private UnidadMateriaFacade getFacade() throws RemoteException {
        return (UnidadMateriaFacade) facadeHandle.getEJBObject();
    }

    protected UnidadMateriaDelegate() throws DelegateException {
        try {
            UnidadMateriaFacadeHome home = UnidadMateriaFacadeUtil.getHome();
            UnidadMateriaFacade remote = home.create();
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
