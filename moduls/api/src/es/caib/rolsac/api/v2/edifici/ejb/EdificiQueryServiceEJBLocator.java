package es.caib.rolsac.api.v2.edifici.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.edifici.ejb.intf.EdificiQueryServiceEJBHome;
import es.caib.rolsac.api.v2.edifici.ejb.intf.EdificiQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class EdificiQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "edifici.ejb.EdificiQueryServiceEJB";
    
    public EdificiQueryServiceEJBRemote getEdificiQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            EdificiQueryServiceEJBHome home = (EdificiQueryServiceEJBHome) PortableRemoteObject.narrow(ref, EdificiQueryServiceEJBHome.class);
            return (EdificiQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), EdificiQueryServiceEJBRemote.class);
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
