package es.caib.rolsac.api.v2.afectacio;

import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;


public interface AfectacioQueryServiceStrategy {
    
    NormativaDTO obtenirAfectant(long idAfectant);
    
    NormativaDTO obtenirNormativa(long idNormativa);
    
    TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio);

}
