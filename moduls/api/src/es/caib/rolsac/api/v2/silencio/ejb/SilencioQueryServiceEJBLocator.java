package es.caib.rolsac.api.v2.silencio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.silencio.ejb.intf.SilencioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.silencio.ejb.intf.SilencioQueryServiceEJBRemote;

public class SilencioQueryServiceEJBLocator extends EJBLocator {
	
	private static final String JNDI_NAME = JNDI_NAME_PREFIX + "silencio.ejb.SilencioQueryServiceEJB";
    
    public SilencioQueryServiceEJBRemote getSilencioQueryServiceEJB() throws LocatorException {
    	
        try {
        	
            Object ref = getRemoteReference(JNDI_NAME);
            SilencioQueryServiceEJBHome home = (SilencioQueryServiceEJBHome)PortableRemoteObject.narrow(ref, SilencioQueryServiceEJBHome.class);
            
            return (SilencioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), SilencioQueryServiceEJBRemote.class);
            
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
