package es.caib.rolsac.api.v2.excepcioDocumentacio.ejb;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ejb.intf.ExcepcioDocumentacioQueryServiceEJBRemote;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.LocatorException;

public class ExcepcioDocumentacioQueryServiceDelegate {

    private ExcepcioDocumentacioQueryServiceEJBLocator excepcioDocumentacioQueryServiceLocator;	

    public void setExcepcioDocumentacioQueryServiceLocator(ExcepcioDocumentacioQueryServiceEJBLocator excepcioDocumentacioQueryServiceLocator) {
        this.excepcioDocumentacioQueryServiceLocator = excepcioDocumentacioQueryServiceLocator;
    }    
        
    public int getNumCatalegsDocuments(long id) throws DelegateException {
    	try {
    		ExcepcioDocumentacioQueryServiceEJBRemote ejb = excepcioDocumentacioQueryServiceLocator.getExcepcioDocumentacioQueryServiceEJB();
    		return ejb.getNumCatalegsDocuments(id);
    	} catch (LocatorException e) {
    		throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
    	} catch (RemoteException e) {
    		throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }
    
    public int getNumDocumentsTramit(long id) throws DelegateException {
        try {
        	ExcepcioDocumentacioQueryServiceEJBRemote ejb = excepcioDocumentacioQueryServiceLocator.getExcepcioDocumentacioQueryServiceEJB();
            return ejb.getNumDocumentsTramit(id);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }
    
    @SuppressWarnings("unchecked")    
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(long id,
			CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws DelegateException {
		
    	ExcepcioDocumentacioQueryServiceEJBRemote ejb;
        try {
            ejb = excepcioDocumentacioQueryServiceLocator.getExcepcioDocumentacioQueryServiceEJB();
            return ejb.llistarCatalegsDocuments(id, catalegDocumentsCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }		    
    
    @SuppressWarnings("unchecked")    
	public List<DocumentTramitDTO> llistarDocumentsTramit(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws DelegateException {
		
    	ExcepcioDocumentacioQueryServiceEJBRemote ejb;
        try {
            ejb = excepcioDocumentacioQueryServiceLocator.getExcepcioDocumentacioQueryServiceEJB();
            return ejb.llistarDocumentsTramits(id, documentTramitCriteria);
        } catch (LocatorException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_SERVICE, e);
        } catch (RemoteException e) {
            throw new DelegateException(ExceptionMessages.REMOTE_CALL, e);
        }
    }		
}