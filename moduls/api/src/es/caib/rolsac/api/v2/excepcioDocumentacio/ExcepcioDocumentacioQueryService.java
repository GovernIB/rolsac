package es.caib.rolsac.api.v2.excepcioDocumentacio;

import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public interface ExcepcioDocumentacioQueryService {

	public int getNumCatalegsDocuments() throws QueryServiceException;
	public int getNumDocumentsTramit() throws QueryServiceException;
	
	public List<CatalegDocumentsQueryServiceAdapter> llistarCatalegsDocuments(CatalegDocumentsCriteria catalegDocumentCriteria) throws QueryServiceException;
	public List<DocumentTramitQueryServiceAdapter> llistarDocumentsTramit(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException;
	
}
