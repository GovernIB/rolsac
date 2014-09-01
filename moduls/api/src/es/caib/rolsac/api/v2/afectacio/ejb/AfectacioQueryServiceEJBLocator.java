package es.caib.rolsac.api.v2.afectacio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.afectacio.ejb.intf.AfectacioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.afectacio.ejb.intf.AfectacioQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class AfectacioQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "afectacio.ejb.AfectacioQueryServiceEJB";
    
    public AfectacioQueryServiceEJBRemote getAfectacioQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            AfectacioQueryServiceEJBHome home = (AfectacioQueryServiceEJBHome) PortableRemoteObject.narrow(ref, AfectacioQueryServiceEJBHome.class);
            return (AfectacioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), AfectacioQueryServiceEJBRemote.class);
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
