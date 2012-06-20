package es.caib.rolsac.api.v2.afectacio.ejb;

import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;

public class AfectacioQueryServiceDelegate {

    private AfectacioQueryServiceEJBLocator afectacioQueryServiceLocator;
    
    public void setAfectacioQueryServiceLocator(AfectacioQueryServiceEJBLocator afectacioQueryServiceLocator) {
        this.afectacioQueryServiceLocator = afectacioQueryServiceLocator;
    }

    public NormativaDTO obtenirAfectant(long idAfectant) {
        AfectacioQueryServiceEJB ejb = afectacioQueryServiceLocator.getAfectacioQueryServiceEJB();
        return ejb.obtenirAfectant(idAfectant);
    }

    public NormativaDTO obtenirNormativa(long idNormativa) {
        AfectacioQueryServiceEJB ejb = afectacioQueryServiceLocator.getAfectacioQueryServiceEJB();
        return ejb.obtenirNormativa(idNormativa);
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio) {
        AfectacioQueryServiceEJB ejb = afectacioQueryServiceLocator.getAfectacioQueryServiceEJB();
        return ejb.obtenirTipusAfectacio(idTipusAfectacio);
    }

}
