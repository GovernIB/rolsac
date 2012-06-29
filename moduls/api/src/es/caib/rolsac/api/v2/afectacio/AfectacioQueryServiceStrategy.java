package es.caib.rolsac.api.v2.afectacio;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;


public interface AfectacioQueryServiceStrategy {
    
    public NormativaDTO obtenirAfectant(long idAfectant) throws StrategyException;
    
    public NormativaDTO obtenirNormativa(long idNormativa) throws StrategyException;
    
    public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio) throws StrategyException;

}
