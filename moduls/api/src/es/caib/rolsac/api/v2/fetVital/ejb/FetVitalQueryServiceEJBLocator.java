package es.caib.rolsac.api.v2.fetVital.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fetVital.ejb.intf.FetVitalQueryServiceEJBHome;
import es.caib.rolsac.api.v2.fetVital.ejb.intf.FetVitalQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.general.EJBLocator;


public class FetVitalQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "fetVital.ejb.FetVitalQueryServiceEJB";
    
    public FetVitalQueryServiceEJBRemote getFetVitalQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            FetVitalQueryServiceEJBHome home = (FetVitalQueryServiceEJBHome) PortableRemoteObject.narrow(ref, FetVitalQueryServiceEJBHome.class);
            return (FetVitalQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), FetVitalQueryServiceEJBRemote.class);
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
