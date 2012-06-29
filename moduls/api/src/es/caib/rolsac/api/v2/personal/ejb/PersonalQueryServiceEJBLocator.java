package es.caib.rolsac.api.v2.personal.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.personal.ejb.intf.PersonalQueryServiceEJBHome;
import es.caib.rolsac.api.v2.personal.ejb.intf.PersonalQueryServiceEJBRemote;

public class PersonalQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "personal.ejb.PersonalQueryServiceEJB";
    
    public PersonalQueryServiceEJBRemote getPersonalQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            PersonalQueryServiceEJBHome home = (PersonalQueryServiceEJBHome) PortableRemoteObject.narrow(ref, PersonalQueryServiceEJBHome.class);
            return (PersonalQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), PersonalQueryServiceEJBRemote.class);
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
