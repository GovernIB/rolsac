package es.caib.rolsac.api.v2.enllac.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.enllac.ejb.intf.EnllacQueryServiceEJBHome;
import es.caib.rolsac.api.v2.enllac.ejb.intf.EnllacQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class EnllacQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "enllac.ejb.EnllacQueryServiceEJB";
    
    public EnllacQueryServiceEJBRemote getEnllacQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            EnllacQueryServiceEJBHome home = (EnllacQueryServiceEJBHome) PortableRemoteObject.narrow(ref, EnllacQueryServiceEJBHome.class);
            return (EnllacQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), EnllacQueryServiceEJBRemote.class);
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
