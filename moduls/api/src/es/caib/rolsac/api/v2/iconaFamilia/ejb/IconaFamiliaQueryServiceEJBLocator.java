package es.caib.rolsac.api.v2.iconaFamilia.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.iconaFamilia.ejb.intf.IconaFamiliaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.iconaFamilia.ejb.intf.IconaFamiliaQueryServiceEJBRemote;

public class IconaFamiliaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "iconaFamilia.ejb.IconaFamiliaQueryServiceEJB";
    
    public IconaFamiliaQueryServiceEJBRemote getIconaFamiliaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            IconaFamiliaQueryServiceEJBHome home = (IconaFamiliaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, IconaFamiliaQueryServiceEJBHome.class);
            return (IconaFamiliaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), IconaFamiliaQueryServiceEJBRemote.class);
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
