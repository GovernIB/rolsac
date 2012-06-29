package es.caib.rolsac.api.v2.documentTramit;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;

public interface DocumentTramitQueryService {
	
    public TramitQueryService obtenirTramit() throws QueryServiceException;
    
    public ArxiuQueryService obtenirArxiu() throws QueryServiceException;
	
}
