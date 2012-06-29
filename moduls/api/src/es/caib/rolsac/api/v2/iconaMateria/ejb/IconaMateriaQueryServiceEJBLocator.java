package es.caib.rolsac.api.v2.iconaMateria.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.iconaMateria.ejb.intf.IconaMateriaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.iconaMateria.ejb.intf.IconaMateriaQueryServiceEJBRemote;


public class IconaMateriaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "iconaMateria.ejb.IconaMateriaQueryServiceEJB";
    
    public IconaMateriaQueryServiceEJBRemote getIconaMateriaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            IconaMateriaQueryServiceEJBHome home = (IconaMateriaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, IconaMateriaQueryServiceEJBHome.class);
            return (IconaMateriaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), IconaMateriaQueryServiceEJBRemote.class);
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
