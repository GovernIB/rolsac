package es.caib.rolsac.api.v2.documentoServicio;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public interface DocumentoServicioQueryService {
	
  //  public NormativaQueryService obtenirNormativa() throws QueryServiceException;
    
    public ArxiuQueryService obtenirArxiu() throws QueryServiceException;
    
    
}
