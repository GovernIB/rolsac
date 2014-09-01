package es.caib.rolsac.api.v2.catalegDocuments;

import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public interface CatalegDocumentsQueryService {
	
	public int getNumDocumentsTramit() throws QueryServiceException;
	public List<DocumentTramitQueryServiceAdapter> llistarDocumentsTramits(DocumentTramitCriteria documentTramitCriteria) throws QueryServiceException;

}
