package es.caib.rolsac.api.v2.documentoNormativa.ejb;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import es.caib.rolsac.api.v2.documentoNormativa.ejb.intf.DocumentoNormativaQueryServiceEJBHome;
import es.caib.rolsac.api.v2.documentoNormativa.ejb.intf.DocumentoNormativaQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.general.EJBLocator;

public class DocumentoNormativaQueryServiceEJBLocator extends EJBLocator {
    
    private static final String JNDI_NAME = JNDI_NAME_PREFIX + "DocumentoNormativa.ejb.DocumentoNormativaQueryServiceEJB";

    public DocumentoNormativaQueryServiceEJBRemote getDocumentoNormativaQueryServiceEJB() throws LocatorException {
        try {
            Object ref = getRemoteReference(JNDI_NAME);
            DocumentoNormativaQueryServiceEJBHome home = (DocumentoNormativaQueryServiceEJBHome) PortableRemoteObject.narrow(ref, DocumentoNormativaQueryServiceEJBHome.class);
            return (DocumentoNormativaQueryServiceEJBRemote) PortableRemoteObject.narrow(home.create(), DocumentoNormativaQueryServiceEJBRemote.class);
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
