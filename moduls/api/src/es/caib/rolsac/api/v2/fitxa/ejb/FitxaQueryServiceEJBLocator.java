package es.caib.rolsac.api.v2.fitxa.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.fitxa.ejb.intf.FitxaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.fitxa.ejb.intf.FitxaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class FitxaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "fitxa.ejb.FitxaQueryServiceEJB";
    
    public FitxaQueryServiceEJBRemote getFitxaQueryServiceEJB() throws LocatorException{
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            FitxaQueryServiceEJBHome home = (FitxaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, FitxaQueryServiceEJBHome.class);
            return (FitxaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), FitxaQueryServiceEJBRemote.class);
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
