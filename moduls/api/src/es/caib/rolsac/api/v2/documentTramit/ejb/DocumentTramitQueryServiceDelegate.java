package es.caib.rolsac.api.v2.documentTramit.ejb;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentTramit.ejb.intf.DocumentTramitQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceDelegate {

    private DocumentTramitQueryServiceEJBLocator documentTramitQueryServiceLocator;
    
    public void setDocumentTramitQueryServiceLocator(DocumentTramitQueryServiceEJBLocator documentTramitQueryServiceEJBLocator) {
        this.documentTramitQueryServiceLocator = documentTramitQueryServiceEJBLocator;
    }

    public TramitDTO obtenirTramit(long idTramit) throws DelegateException {
        try {
            DocumentTramitQueryServiceEJBRemote ejb = documentTramitQueryServiceLocator.getDocumentTramitQueryServiceEJB();
            return ejb.obtenirTramit(idTramit);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) throws DelegateException {
        try {
            DocumentTramitQueryServiceEJBRemote ejb = documentTramitQueryServiceLocator.getDocumentTramitQueryServiceEJB();
            return ejb.obtenirArxiu(idArxiu);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
}
