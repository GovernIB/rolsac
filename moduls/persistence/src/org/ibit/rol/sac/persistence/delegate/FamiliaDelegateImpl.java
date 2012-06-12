package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.intf.FamiliaFacade;
import org.ibit.rol.sac.persistence.intf.FamiliaFacadeHome;
import org.ibit.rol.sac.persistence.util.FamiliaFacadeUtil;
import org.ibit.rol.sac.model.Familia;

import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular familias.
 */
public class FamiliaDelegateImpl implements StatelessDelegate, FamiliaDelegateI{

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FamiliaDelegateI#grabarFamilia(org.ibit.rol.sac.model.Familia)
	 */
    public Long grabarFamilia(Familia familia) throws DelegateException {
        try {
            return getFacade().grabarFamilia(familia);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FamiliaDelegateI#listarFamilias()
	 */
    public List listarFamilias() throws DelegateException {
        try {
            return getFacade().listarFamilias();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FamiliaDelegateI#obtenerFamilia(java.lang.Long)
	 */
    public Familia obtenerFamilia(Long id) throws DelegateException {
        try {
            return getFacade().obtenerFamilia(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FamiliaDelegateI#tieneProcedimientos(java.lang.Long)
	 */
    public boolean tieneProcedimientos(Long id) throws DelegateException {
        try {
            return getFacade().tieneProcedimientos(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.FamiliaDelegateI#borrarFamilia(java.lang.Long)
	 */
    public void borrarFamilia(Long id) throws DelegateException {
        try {
            getFacade().borrarFamilia(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private FamiliaFacade getFacade() throws RemoteException {
        return (FamiliaFacade) facadeHandle.getEJBObject();
    }

    protected FamiliaDelegateImpl() throws DelegateException {
        try {
            FamiliaFacadeHome home = FamiliaFacadeUtil.getHome();
            FamiliaFacade remote = home.create();
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
