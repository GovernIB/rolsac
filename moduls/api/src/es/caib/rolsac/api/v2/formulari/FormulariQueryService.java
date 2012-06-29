package es.caib.rolsac.api.v2.formulari;

import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public interface FormulariQueryService {

    public ArxiuQueryServiceAdapter obtenirArchivo() throws QueryServiceException;
    
    public ArxiuQueryServiceAdapter obtenirManual() throws QueryServiceException;
    
    public TramitQueryServiceAdapter obtenirTramit() throws QueryServiceException;

}
