package es.caib.rolsac.api.v2.excepcioDocumentacio.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class ExcepcioDocumentacioQueryServiceEJBStrategy implements ExcepcioDocumentacioQueryServiceStrategy {

	private ExcepcioDocumentacioQueryServiceDelegate excepcioDocumentacioQueryServiceDelegate;
	
    public void setExcepcioDocumentacioQueryServiceDelegate(ExcepcioDocumentacioQueryServiceDelegate excepcioDocumentacioQueryServiceDelegate) {
        this.excepcioDocumentacioQueryServiceDelegate = excepcioDocumentacioQueryServiceDelegate;
    }

	public int getNumCatalegsDocuments(long id) throws StrategyException {
        try {
            return excepcioDocumentacioQueryServiceDelegate.getNumCatalegsDocuments(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }				
	}

	public int getNumDocumentsTramit(long id) throws StrategyException {
        try {
            return excepcioDocumentacioQueryServiceDelegate.getNumDocumentsTramit(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }		
	}
	
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(long id,
			CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws StrategyException {
		
		try {
			return excepcioDocumentacioQueryServiceDelegate.llistarCatalegsDocuments(id, catalegDocumentsCriteria);
		} catch (DelegateException e) {
			throw new StrategyException(e);
		}
		
	}
	
	public List<DocumentTramitDTO> llistarDocumentsTramit(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		
        try {
            return excepcioDocumentacioQueryServiceDelegate.llistarDocumentsTramit(id, documentTramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
		
	}

	public void setUrl(String url) {
		// No es necesario en ejb			
	}

}
