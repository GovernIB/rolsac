package es.caib.rolsac.api.v2.agrupacioFetVital.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.agrupacioFetVital.ejb.intf.AgrupacioFetVitalQueryServiceEJBHome;
import es.caib.rolsac.api.v2.agrupacioFetVital.ejb.intf.AgrupacioFetVitalQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class AgrupacioFetVitalQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "agrupacioFetVital.ejb.AgrupacioFetVitalQueryServiceEJB";
    
    public AgrupacioFetVitalQueryServiceEJBRemote getAgrupacioFetVitalQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            AgrupacioFetVitalQueryServiceEJBHome home = (AgrupacioFetVitalQueryServiceEJBHome) PortableRemoteObject.narrow(ref, AgrupacioFetVitalQueryServiceEJBHome.class);
            return (AgrupacioFetVitalQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), AgrupacioFetVitalQueryServiceEJBRemote.class);
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
