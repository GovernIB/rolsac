package es.caib.rolsac.api.v2.catalegDocuments.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.catalegDocuments.ejb.intf.CatalegDocumentsQueryServiceEJBHome;
import es.caib.rolsac.api.v2.catalegDocuments.ejb.intf.CatalegDocumentsQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class CatalegDocumentsQueryServiceEJBLocator extends EJBLocator {
	
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "catalegDocuments.ejb.CatalegDocumentsQueryServiceEJB";
    
    public CatalegDocumentsQueryServiceEJBRemote getCatalegDocumentsQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            CatalegDocumentsQueryServiceEJBHome home = (CatalegDocumentsQueryServiceEJBHome) PortableRemoteObject.narrow(ref, CatalegDocumentsQueryServiceEJBHome.class);
            return (CatalegDocumentsQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), CatalegDocumentsQueryServiceEJBRemote.class);
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
