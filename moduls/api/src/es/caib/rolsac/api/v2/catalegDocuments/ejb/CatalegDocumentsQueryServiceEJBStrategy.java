package es.caib.rolsac.api.v2.catalegDocuments.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryServiceStrategy;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class CatalegDocumentsQueryServiceEJBStrategy implements CatalegDocumentsQueryServiceStrategy {

	private CatalegDocumentsQueryServiceDelegate catalegDocumentsQueryServiceDelegate;
	
    public void setCatalegDocumentsQueryServiceDelegate(CatalegDocumentsQueryServiceDelegate catalegDocumentsQueryServiceDelegate) {
        this.catalegDocumentsQueryServiceDelegate = catalegDocumentsQueryServiceDelegate;
    }	
	
	public List<DocumentTramitDTO> llistarDocumentsTramits(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		
        try {
            return catalegDocumentsQueryServiceDelegate.llistarDocumentsTramits(id, documentTramitCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
		
	}

	public int getNumDocumentsTramit(long id) throws StrategyException {
        try {
            return catalegDocumentsQueryServiceDelegate.getNumDocumentsTramits(id);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
}
