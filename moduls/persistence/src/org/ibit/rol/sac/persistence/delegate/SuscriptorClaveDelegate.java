package org.ibit.rol.sac.persistence.delegate;




import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.SuscriptorClave;
import org.ibit.rol.sac.persistence.intf.SuscriptorClaveFacade;
import org.ibit.rol.sac.persistence.intf.SuscriptorClaveFacadeHome;
import org.ibit.rol.sac.persistence.util.SuscriptorClaveFacadeUtil;

/**
 * @deprecated Se usa únicamente desde el back antiguo.
 * Business delegate para manipular materias.
 */
public class SuscriptorClaveDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */
    
	/** @deprecated No se usa */
    public void init() throws DelegateException {
        try {
        	getFacade().init();
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }	
	
    /** @deprecated No se usa */
    public void init(Long id) throws DelegateException {
        try {
        	getFacade().init(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    /** @deprecated se usa desde el back antiguo */
	public Long grabarSuscriptorClave(SuscriptorClave suscriptor)  throws DelegateException	{
        try {
            return getFacade().grabarSuscriptorClave(suscriptor);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

	/** @deprecated se usa desde el back antiguo */
	public Long recuperarSuscriptorClave( Long tipo, String email)  throws DelegateException	 {	
        try {
            return getFacade().recuperarSuscriptorClave(tipo, email);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated No se usa */
	public boolean esSuscriptorCaib( String email, String clave)  throws DelegateException	 {	
        try {
            return getFacade().esSuscriptorCaib(email, clave);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
	
    /** @deprecated No se usa */
    public SuscriptorClave obtenerSuscriptor(Long id) throws DelegateException {
        try {
            return getFacade().obtenerSuscriptorClave(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

    /** @deprecated No se usa */
    public void borrarSuscriptorClave(Long id) throws DelegateException {
        try {
        	getFacade().borrarSuscriptorClave(id);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }
    

    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private SuscriptorClaveFacade getFacade() throws RemoteException {
        return (SuscriptorClaveFacade) facadeHandle.getEJBObject();
    }

    protected SuscriptorClaveDelegate() throws DelegateException {
        try {
        	SuscriptorClaveFacadeHome home = SuscriptorClaveFacadeUtil.getHome();
        	SuscriptorClaveFacade remote = home.create();
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
