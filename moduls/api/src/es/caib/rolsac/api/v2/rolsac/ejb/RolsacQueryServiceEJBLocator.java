package es.caib.rolsac.api.v2.rolsac.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.rolsac.ejb.intf.RolsacQueryServiceEJBHome;
import es.caib.rolsac.api.v2.rolsac.ejb.intf.RolsacQueryServiceEJBRemote;

public class RolsacQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "rolsac.ejb.RolsacQueryServiceEJB";

    public RolsacQueryServiceEJBRemote getRolsacQueryServiceEJB() throws LocatorException {
        try {
        	Object ref = getRemoteReference(JNDI_NAME); 
            //RolsacQueryServiceEJBHome home = (RolsacQueryServiceEJBHome) PortableRemoteObject.narrow(ref, RolsacQueryServiceEJBHome.class);
            //return (RolsacQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), RolsacQueryServiceEJBRemote.class);
            RolsacQueryServiceEJBHome home = (RolsacQueryServiceEJBHome) ref;
            return home.create();
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
