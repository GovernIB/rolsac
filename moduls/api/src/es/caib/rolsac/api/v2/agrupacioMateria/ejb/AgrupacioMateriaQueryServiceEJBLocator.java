package es.caib.rolsac.api.v2.agrupacioMateria.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.agrupacioMateria.ejb.intf.AgrupacioMateriaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.agrupacioMateria.ejb.intf.AgrupacioMateriaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class AgrupacioMateriaQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "agrupacioMateria.ejb.AgrupacioMateriaQueryServiceEJB";
    
    public AgrupacioMateriaQueryServiceEJBRemote getAgrupacioMateriaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            AgrupacioMateriaQueryServiceEJBHome home = (AgrupacioMateriaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, AgrupacioMateriaQueryServiceEJBHome.class);
            return (AgrupacioMateriaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), AgrupacioMateriaQueryServiceEJBRemote.class);
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
