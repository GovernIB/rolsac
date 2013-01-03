package es.caib.rolsac.api.v2.catalegDocuments.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.ejb.intf.CatalegDocumentsQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;

public class CatalegDocumentsQueryServiceDelegate {
	
    private CatalegDocumentsQueryServiceEJBLocator catalegDocumentsQueryServiceLocator;	

    public void setCatalegDocumentsQueryServiceLocator(CatalegDocumentsQueryServiceEJBLocator catalegDocumentsQueryServiceLocator) {
        this.catalegDocumentsQueryServiceLocator = catalegDocumentsQueryServiceLocator;
    }    
        
    public int getNumDocumentsTramits(long id) throws DelegateException {
        try {
        	CatalegDocumentsQueryServiceEJBRemote ejb = catalegDocumentsQueryServiceLocator.getCatalegDocumentsQueryServiceEJB();
            return ejb.getNumDocumentsTramit(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")    
	public List<DocumentTramitDTO> llistarDocumentsTramits(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws DelegateException {
		
        CatalegDocumentsQueryServiceEJBRemote ejb;
        try {
            ejb = catalegDocumentsQueryServiceLocator.getCatalegDocumentsQueryServiceEJB();
            return ejb.llistarDocumentsTramits(id, documentTramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
        
    }
}