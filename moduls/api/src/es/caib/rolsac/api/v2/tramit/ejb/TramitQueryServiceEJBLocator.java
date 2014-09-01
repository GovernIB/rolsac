package es.caib.rolsac.api.v2.tramit.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.tramit.ejb.intf.TramitQueryServiceEJBHome;
import es.caib.rolsac.api.v2.tramit.ejb.intf.TramitQueryServiceEJBRemote;

public class TramitQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "tramit.ejb.TramitQueryServiceEJB";
    
    public TramitQueryServiceEJBRemote getTramitQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            TramitQueryServiceEJBHome home = (TramitQueryServiceEJBHome) PortableRemoteObject.narrow(ref, TramitQueryServiceEJBHome.class);
            return (TramitQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), TramitQueryServiceEJBRemote.class);
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
