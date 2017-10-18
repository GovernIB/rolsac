package es.caib.rolsac.api.v2.servicio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.servicio.ejb.intf.ServicioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.servicio.ejb.intf.ServicioQueryServiceEJBRemote;

public class ServicioQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "servicio.ejb.ServicioQueryServiceEJB";
    
    public ServicioQueryServiceEJBRemote getServicioQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            ServicioQueryServiceEJBHome home = (ServicioQueryServiceEJBHome) PortableRemoteObject.narrow(ref, ServicioQueryServiceEJBHome.class);
            return (ServicioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), ServicioQueryServiceEJBRemote.class);
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
