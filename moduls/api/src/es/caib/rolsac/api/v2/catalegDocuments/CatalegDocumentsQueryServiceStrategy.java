package es.caib.rolsac.api.v2.catalegDocuments;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;

public interface CatalegDocumentsQueryServiceStrategy {
	
	public int getNumDocumentsTramit(long id) throws StrategyException;
	public List<DocumentTramitDTO> llistarDocumentsTramits(long id, DocumentTramitCriteria documentTramitCriteria) throws StrategyException;	

}
