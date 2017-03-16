package es.caib.rolsac.api.v2.afectacio.ejb;

import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;

public class AfectacioQueryServiceEJBStrategy implements AfectacioQueryServiceStrategy {
    
    AfectacioQueryServiceDelegate afectacioQueryServiceDelegate;

    public void setAfectacioQueryServiceDelegate(AfectacioQueryServiceDelegate afectacioQueryServiceDelegate) {
        this.afectacioQueryServiceDelegate = afectacioQueryServiceDelegate;
    }

    public NormativaDTO obtenirAfectant(long idAfectant) throws StrategyException {
        try {
            return afectacioQueryServiceDelegate.obtenirAfectant(idAfectant);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public NormativaDTO obtenirNormativa(long idNormativa) throws StrategyException {
        try {
            return afectacioQueryServiceDelegate.obtenirNormativa(idNormativa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio) throws StrategyException {
        try {
            return afectacioQueryServiceDelegate.obtenirTipusAfectacio(idTipusAfectacio);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en los EJBs.
	}

}
