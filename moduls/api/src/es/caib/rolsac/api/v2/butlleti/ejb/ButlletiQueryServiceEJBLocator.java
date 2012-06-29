package es.caib.rolsac.api.v2.butlleti.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.butlleti.ejb.intf.ButlletiQueryServiceEJBHome;
import es.caib.rolsac.api.v2.butlleti.ejb.intf.ButlletiQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class ButlletiQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "butlleti.ejb.ButlletiQueryServiceEJB";
    
    public ButlletiQueryServiceEJBRemote getButlletiQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            ButlletiQueryServiceEJBHome home = (ButlletiQueryServiceEJBHome) PortableRemoteObject.narrow(ref, ButlletiQueryServiceEJBHome.class);
            return (ButlletiQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), ButlletiQueryServiceEJBRemote.class);
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
