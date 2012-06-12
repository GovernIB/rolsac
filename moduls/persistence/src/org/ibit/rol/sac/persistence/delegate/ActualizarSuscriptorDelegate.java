package org.ibit.rol.sac.persistence.delegate;




import javax.ejb.Handle;
import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.ibit.rol.sac.model.Suscriptor;
import org.ibit.rol.sac.persistence.intf.ActualizarSuscriptorFacade;
import org.ibit.rol.sac.persistence.intf.ActualizarSuscriptorFacadeHome;
import org.ibit.rol.sac.persistence.util.ActualizarSuscriptorFacadeUtil;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

/**
 * Business delegate para manipular materias.
 */
public class ActualizarSuscriptorDelegate implements StatelessDelegate {

    /* ========================================================= */
    /* ======================== MÉTODOS DE NEGOCIO ============= */
    /* ========================================================= */

	


    public void actualizaSuscriptor(Suscriptor sus) throws DelegateException {
    	try {
    		getFacade().actualizaSuscriptor(sus);
    	} catch (RemoteException e) {
    		throw new DelegateException(e);
    	}
    }

   
        
    /* ========================================================= */
    /* ======================== REFERENCIA AL FACADE  ========== */
    /* ========================================================= */

    private Handle facadeHandle;

    private ActualizarSuscriptorFacade getFacade() throws RemoteException {
        return (ActualizarSuscriptorFacade) facadeHandle.getEJBObject();
    }

    protected ActualizarSuscriptorDelegate() throws DelegateException {
        try {
        	ActualizarSuscriptorFacadeHome home = ActualizarSuscriptorFacadeUtil.getHome();
        	ActualizarSuscriptorFacade remote = home.create();
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
