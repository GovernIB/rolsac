package es.caib.rolsac.api.v2.normativa.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.normativa.ejb.intf.NormativaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.normativa.ejb.intf.NormativaQueryServiceEJBRemote;


public class NormativaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "normativa.ejb.NormativaQueryServiceEJB";
    
    public NormativaQueryServiceEJBRemote getNormativaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            NormativaQueryServiceEJBHome home = (NormativaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, NormativaQueryServiceEJBHome.class);
            return (NormativaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), NormativaQueryServiceEJBRemote.class);
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
