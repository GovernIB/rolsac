package es.caib.rolsac.api.v2.afectacio;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioQueryServiceAdapter;

public interface AfectacioQueryService {

    public NormativaQueryServiceAdapter obtenirAfectant() throws QueryServiceException;
    
    public NormativaQueryServiceAdapter obtenirNormativa() throws QueryServiceException;
    
    public TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio() throws QueryServiceException;
}
