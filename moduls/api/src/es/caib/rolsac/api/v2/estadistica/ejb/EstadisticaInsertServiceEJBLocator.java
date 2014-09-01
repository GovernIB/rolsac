package es.caib.rolsac.api.v2.estadistica.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.estadistica.ejb.intf.EstadisticaInsertServiceEJBHome;
import es.caib.rolsac.api.v2.estadistica.ejb.intf.EstadisticaInsertServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class EstadisticaInsertServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "estadistica.ejb.EstadisticaInsertServiceEJB";
    
    public EstadisticaInsertServiceEJBRemote getEstadisticaInsertServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            EstadisticaInsertServiceEJBHome home = (EstadisticaInsertServiceEJBHome) PortableRemoteObject.narrow(ref, EstadisticaInsertServiceEJBHome.class);
            return (EstadisticaInsertServiceEJBRemote) PortableRemoteObject.narrow(home.create(), EstadisticaInsertServiceEJBRemote.class);
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
