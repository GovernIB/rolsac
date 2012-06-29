package es.caib.rolsac.api.v2.publicObjectiu.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.publicObjectiu.ejb.intf.PublicObjectiuQueryServiceEJBHome;
import es.caib.rolsac.api.v2.publicObjectiu.ejb.intf.PublicObjectiuQueryServiceEJBRemote;

public class PublicObjectiuQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "publicObjectiu.ejb.PublicObjectiuQueryServiceEJB";

    public PublicObjectiuQueryServiceEJBRemote getPublicObjectiuQueryServceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            PublicObjectiuQueryServiceEJBHome home = (PublicObjectiuQueryServiceEJBHome) PortableRemoteObject.narrow(ref, PublicObjectiuQueryServiceEJBHome.class);
            return (PublicObjectiuQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), PublicObjectiuQueryServiceEJBRemote.class);
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
