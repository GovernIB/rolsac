package org.ibit.rol.sac.persistence.delegate;

import org.ibit.rol.sac.persistence.intf.IniciacionFacade;
import org.ibit.rol.sac.persistence.intf.IniciacionFacadeHome;
import org.ibit.rol.sac.persistence.util.IniciacionFacadeUtil;
import org.ibit.rol.sac.model.Iniciacion;
 
import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Business delegate para manipular Iniciacion.
 */
public class IniciacionDelegateImpl implements StatelessDelegate, IniciacionDelegateI {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

    

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IniciacionDelegateI#obtenerIniciacion(java.lang.Long)
	 */
    public Iniciacion obtenerIniciacion(Long id) throws DelegateException {
        try {
            return getFacade().obtenerIniciacion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IniciacionDelegateI#listarIniciacion()
	 */
    public List listarIniciacion() throws DelegateException {
        try {
            return getFacade().listarIniciacion();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IniciacionDelegateI#grabarIniciacion(org.ibit.rol.sac.model.Iniciacion)
	 */
    public Long grabarIniciacion(Iniciacion tipo ) throws DelegateException {
        try {
            return getFacade().grabarIniciacion(tipo);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
 
     

    /* (non-Javadoc)
	 * @see org.ibit.rol.sac.persistence.delegate.IniciacionDelegateI#borrarIniciacion(java.lang.Long)
	 */
    public void borrarIniciacion(Long id) throws DelegateException {
        try {
            getFacade().borrarIniciacion(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private IniciacionFacade getFacade() throws RemoteException {
        return (IniciacionFacade) facadeHandle.getEJBObject();
    }

    protected IniciacionDelegateImpl() throws DelegateException {
        try {
        	IniciacionFacadeHome home = IniciacionFacadeUtil.getHome();
        	IniciacionFacade remote = home.create();
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
