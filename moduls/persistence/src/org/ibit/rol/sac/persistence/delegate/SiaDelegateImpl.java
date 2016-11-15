package org.ibit.rol.sac.persistence.delegate;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.Handle;
import javax.naming.NamingException;

import org.ibit.rol.sac.persistence.intf.SiaPendienteFacade;
import org.ibit.rol.sac.persistence.intf.SiaPendienteFacadeHome;
import org.ibit.rol.sac.persistence.util.SiaPendienteFacadeUtil;

/**
 * Business delegate pera enviar sia.
 */
public class SiaDelegateImpl extends SiaDelegate implements  StatelessDelegate, SiaDelegateI {

    private Handle facadeHandle;

    private SiaPendienteFacade getFacade() throws RemoteException {
        return (SiaPendienteFacade) facadeHandle.getEJBObject();
    }

    protected SiaDelegateImpl() throws DelegateException {
        try {
        	SiaPendienteFacadeHome home = SiaPendienteFacadeUtil.getHome();
        	SiaPendienteFacade remote = home.create();
            facadeHandle = remote.getHandle();
        } catch (NamingException e) {
            throw new DelegateException(e);
        } catch (CreateException e) {
            throw new DelegateException(e);
        } catch (RemoteException e) {
            throw new DelegateException(e);
        }
    }

 
	@Override
	public  void enviarTodos()  throws DelegateException{
	
	   try {
		   getFacade().enviarTodos();
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}

	@Override
	public  void enviarPendientes()  throws DelegateException{
	
	   try {
		   getFacade().enviarPendientes();
       } catch (RemoteException e) {
           throw new DelegateException(e);
       }
	}
	



	

	

	
}
