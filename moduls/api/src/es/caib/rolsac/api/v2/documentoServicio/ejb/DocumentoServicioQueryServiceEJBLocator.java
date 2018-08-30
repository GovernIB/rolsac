package es.caib.rolsac.api.v2.documentoServicio.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.documentoServicio.ejb.intf.DocumentoServicioQueryServiceEJBHome;
import es.caib.rolsac.api.v2.documentoServicio.ws.DocumentoServicioQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class DocumentoServicioQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "documentoServicio.ejb.DocumentoServicioQueryServiceEJB";

    public DocumentoServicioQueryServiceEJBRemote getDocumentoServicioQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            DocumentoServicioQueryServiceEJBHome home = (DocumentoServicioQueryServiceEJBHome) PortableRemoteObject.narrow(ref, DocumentoServicioQueryServiceEJBHome.class);
            return (DocumentoServicioQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), DocumentoServicioQueryServiceEJBRemote.class);
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
