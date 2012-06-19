package es.caib.rolsac.api.v2.afectacio;

import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioQueryServiceAdapter;

public interface AfectacioQueryService {

    NormativaQueryServiceAdapter obtenirAfectant();
    
    NormativaQueryServiceAdapter obtenirNormativa();
    
    TipusAfectacioQueryServiceAdapter obtenirTipusAfectacio();
}
