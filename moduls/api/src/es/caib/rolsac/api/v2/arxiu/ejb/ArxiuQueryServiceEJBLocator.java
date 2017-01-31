package es.caib.rolsac.api.v2.arxiu.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;



import es.caib.rolsac.api.v2.arxiu.ejb.intf.ArxiuQueryServiceEJBHome;
import es.caib.rolsac.api.v2.arxiu.ejb.intf.ArxiuQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class ArxiuQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME =  "es.caib.rolsac.api.v2.arxiu.ejb.ArxiuQueryServiceEJB";
    
    public ArxiuQueryServiceEJBRemote getArxiuQueryServiceEJB() throws LocatorException {
        try {
    
            Object ref = getRemoteReference(JNDI_NAME);
            ArxiuQueryServiceEJBHome home = (ArxiuQueryServiceEJBHome) PortableRemoteObject.narrow(ref, ArxiuQueryServiceEJBHome.class);
            return (ArxiuQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), ArxiuQueryServiceEJBRemote.class);
        }catch (NamingException e) {
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
