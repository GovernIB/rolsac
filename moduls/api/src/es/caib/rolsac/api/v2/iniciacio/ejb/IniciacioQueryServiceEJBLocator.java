package es.caib.rolsac.api.v2.iniciacio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.iniciacio.ejb.intf.IniciacioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.iniciacio.ejb.intf.IniciacioQueryServiceEJBRemote;

public class IniciacioQueryServiceEJBLocator extends EJBLocator {
	
	private static final String JNDI_NAME = JNDI_NAME_PREFIX + "iniciacio.ejb.IniciacioQueryServiceEJB";
    
    public IniciacioQueryServiceEJBRemote getIniciacioQueryServiceEJB() throws LocatorException {
    	
        try {
        	
            Object ref = getRemoteReference(JNDI_NAME);
            IniciacioQueryServiceEJBHome home = (IniciacioQueryServiceEJBHome)PortableRemoteObject.narrow(ref, IniciacioQueryServiceEJBHome.class);
            
            return (IniciacioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), IniciacioQueryServiceEJBRemote.class);
            
        } catch (NamingException e) {
        	
            throw new LocatorException(ExceptionMessages.JNDI_NAMING, e);
            
        } catch (RemoteException e) {
        	
            throw new LocatorException(ExceptionMessages.JNDI_REMOTE, e);
            
        } catch (CreateException e) {
        	
            throw new LocatorException(ExceptionMessages.EJB_CREATE, e);
            
        } catch (ClassCastException e) {
        	
            throw new LocatorException(ExceptionMessages.EJB_CLASS_CAST, e);
            
        }
        
    }

}
