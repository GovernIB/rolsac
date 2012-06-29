package es.caib.rolsac.api.v2.fitxaUA.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fitxaUA.ejb.intf.FitxaUAQueryServiceEJBHome;
import es.caib.rolsac.api.v2.fitxaUA.ejb.intf.FitxaUAQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.general.EJBLocator;


public class FitxaUAQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "fitxaUA.ejb.FitxaUAQueryServiceEJB";

    public FitxaUAQueryServiceEJBRemote getFitxaUAQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            FitxaUAQueryServiceEJBHome home = (FitxaUAQueryServiceEJBHome) PortableRemoteObject.narrow(ref, FitxaUAQueryServiceEJBHome.class);
            return (FitxaUAQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), FitxaUAQueryServiceEJBRemote.class);
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
