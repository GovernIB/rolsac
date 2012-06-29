package es.caib.rolsac.api.v2.espaiTerritorial.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.espaiTerritorial.ejb.intf.EspaiTerritorialQueryServiceEJBHome;
import es.caib.rolsac.api.v2.espaiTerritorial.ejb.intf.EspaiTerritorialQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class EspaiTerritorialQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "espaiTerritorial.ejb.EspaiTerritorialQueryServiceEJB";
    
    public EspaiTerritorialQueryServiceEJBRemote getEspaiTerritorialQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            EspaiTerritorialQueryServiceEJBHome home = (EspaiTerritorialQueryServiceEJBHome) PortableRemoteObject.narrow(ref, EspaiTerritorialQueryServiceEJBHome.class);
            return (EspaiTerritorialQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), EspaiTerritorialQueryServiceEJBRemote.class);
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
