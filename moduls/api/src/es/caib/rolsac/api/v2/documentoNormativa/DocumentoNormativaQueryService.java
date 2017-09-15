package es.caib.rolsac.api.v2.documentoNormativa;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService;

public interface DocumentoNormativaQueryService {
	
    public NormativaQueryService obtenirNormativa() throws QueryServiceException;
    
    public ArxiuQueryService obtenirArxiu() throws QueryServiceException;
    
    
}
