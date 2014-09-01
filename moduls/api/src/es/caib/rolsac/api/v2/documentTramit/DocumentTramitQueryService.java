package es.caib.rolsac.api.v2.documentTramit;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryService;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioQueryService;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;

public interface DocumentTramitQueryService {
	
    public TramitQueryService obtenirTramit() throws QueryServiceException;
    
    public ArxiuQueryService obtenirArxiu() throws QueryServiceException;
    
    public CatalegDocumentsQueryService obtenirCatalegDocument() throws QueryServiceException;
    
    public ExcepcioDocumentacioQueryService obtenirExcepcioDocumentacio() throws QueryServiceException;
    
}
