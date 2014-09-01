package es.caib.rolsac.api.v2.unitatMateria.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.unitatMateria.ejb.intf.UnitatMateriaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.unitatMateria.ejb.intf.UnitatMateriaQueryServiceEJBRemote;


public class UnitatMateriaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "unitatMateria.ejb.UnitatMateriaQueryServiceEJB";
    
    public UnitatMateriaQueryServiceEJBRemote getUnitatMateriaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            UnitatMateriaQueryServiceEJBHome home = (UnitatMateriaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, UnitatMateriaQueryServiceEJBHome.class);
            return (UnitatMateriaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), UnitatMateriaQueryServiceEJBRemote.class);
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
