package es.caib.rolsac.api.v2.seccio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.seccio.ejb.intf.SeccioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.seccio.ejb.intf.SeccioQueryServiceEJBRemote;

public class SeccioQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "seccio.ejb.SeccioQueryServiceEJB";

    public SeccioQueryServiceEJBRemote getSeccioQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            SeccioQueryServiceEJBHome home = (SeccioQueryServiceEJBHome) PortableRemoteObject.narrow(ref, SeccioQueryServiceEJBHome.class);
            return (SeccioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), SeccioQueryServiceEJBRemote.class);
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
