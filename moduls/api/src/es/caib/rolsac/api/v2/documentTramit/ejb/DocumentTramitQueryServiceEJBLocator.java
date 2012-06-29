package es.caib.rolsac.api.v2.documentTramit.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.documentTramit.ejb.intf.DocumentTramitQueryServiceEJBHome;
import es.caib.rolsac.api.v2.documentTramit.ejb.intf.DocumentTramitQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class DocumentTramitQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "documentTramit.ejb.DocumentTramitQueryServiceEJB";

    public DocumentTramitQueryServiceEJBRemote getDocumentTramitQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            DocumentTramitQueryServiceEJBHome home = (DocumentTramitQueryServiceEJBHome) PortableRemoteObject.narrow(ref, DocumentTramitQueryServiceEJBHome.class);
            return (DocumentTramitQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), DocumentTramitQueryServiceEJBRemote.class);
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
