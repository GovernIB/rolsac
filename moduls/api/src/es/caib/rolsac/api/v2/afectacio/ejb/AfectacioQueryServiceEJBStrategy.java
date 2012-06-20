package es.caib.rolsac.api.v2.afectacio.ejb;

import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceStrategy;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;

public class AfectacioQueryServiceEJBStrategy implements AfectacioQueryServiceStrategy {
    
    AfectacioQueryServiceDelegate afectacioQueryServiceDelegate;

    public void setAfectacioQueryServiceDelegate(AfectacioQueryServiceDelegate afectacioQueryServiceDelegate) {
        this.afectacioQueryServiceDelegate = afectacioQueryServiceDelegate;
    }

    public NormativaDTO obtenirAfectant(long idAfectant) {
        return afectacioQueryServiceDelegate.obtenirAfectant(idAfectant);
    }

    public NormativaDTO obtenirNormativa(long idNormativa) {
        return afectacioQueryServiceDelegate.obtenirNormativa(idNormativa);
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio) {
        return afectacioQueryServiceDelegate.obtenirTipusAfectacio(idTipusAfectacio);
    }

}
