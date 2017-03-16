package es.caib.rolsac.api.v2.excepcioDocumentacio;

import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;

public interface ExcepcioDocumentacioQueryServiceStrategy {

	public int getNumCatalegsDocuments(long id) throws StrategyException;
	public int getNumDocumentsTramit(long id) throws StrategyException;
	
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(long id, CatalegDocumentsCriteria catalegDocumentCriteria) throws StrategyException;
	public List<DocumentTramitDTO> llistarDocumentsTramit(long id, DocumentTramitCriteria documentTramitCriteria) throws StrategyException;
	public void setUrl(String url);
	
}
