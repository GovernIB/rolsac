package es.caib.rolsac.api.v2.unitatAdministrativa.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.unitatAdministrativa.ejb.intf.UnitatAdministrativaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.unitatAdministrativa.ejb.intf.UnitatAdministrativaQueryServiceEJBRemote;

public class UnitatAdministrativaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "unitatAdministrativa.ejb.UnitatAdministrativaQueryServiceEJB";
    
    public UnitatAdministrativaQueryServiceEJBRemote getUnitatAdministrativaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            UnitatAdministrativaQueryServiceEJBHome home = (UnitatAdministrativaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, UnitatAdministrativaQueryServiceEJBHome.class);
            return (UnitatAdministrativaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), UnitatAdministrativaQueryServiceEJBRemote.class);
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
