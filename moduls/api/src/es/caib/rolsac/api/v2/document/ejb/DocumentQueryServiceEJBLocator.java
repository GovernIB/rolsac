package es.caib.rolsac.api.v2.document.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.document.ejb.intf.DocumentQueryServiceEJBHome;
import es.caib.rolsac.api.v2.document.ejb.intf.DocumentQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class DocumentQueryServiceEJBLocator extends EJBLocator {

    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "document.ejb.DocumentQueryServiceEJB";

    public DocumentQueryServiceEJBRemote getDocumentQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            DocumentQueryServiceEJBHome home = (DocumentQueryServiceEJBHome) PortableRemoteObject.narrow(ref, DocumentQueryServiceEJBHome.class);
            return (DocumentQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), DocumentQueryServiceEJBRemote.class);
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
