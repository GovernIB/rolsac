package es.caib.rolsac.api.v2.documentTramit;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;

public interface DocumentTramitQueryService {
	
    public TramitQueryService obtenirTramit();
    
    public ArxiuQueryService obtenirArxiu();
	
}
