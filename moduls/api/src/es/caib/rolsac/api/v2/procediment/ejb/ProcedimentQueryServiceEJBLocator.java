package es.caib.rolsac.api.v2.procediment.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;
import es.caib.rolsac.api.v2.procediment.ejb.intf.ProcedimentQueryServiceEJBHome;
import es.caib.rolsac.api.v2.procediment.ejb.intf.ProcedimentQueryServiceEJBRemote;

public class ProcedimentQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "procediment.ejb.ProcedimentQueryServiceEJB";
    
    public ProcedimentQueryServiceEJBRemote getProcedimentQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            ProcedimentQueryServiceEJBHome home = (ProcedimentQueryServiceEJBHome) PortableRemoteObject.narrow(ref, ProcedimentQueryServiceEJBHome.class);
            return (ProcedimentQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), ProcedimentQueryServiceEJBRemote.class);
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
