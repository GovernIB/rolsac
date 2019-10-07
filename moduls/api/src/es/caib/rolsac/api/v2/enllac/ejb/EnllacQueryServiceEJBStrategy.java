package es.caib.rolsac.api.v2.enllac.ejb;

import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class EnllacQueryServiceEJBStrategy implements EnllacQueryServiceStrategy {

    EnllacQueryServiceDelegate enllacQueryServiceDelegate;

    public void setEnllacQueryServiceDelegate(EnllacQueryServiceDelegate enllacQueryServiceDelegate) {
        this.enllacQueryServiceDelegate = enllacQueryServiceDelegate;
    }

    public FitxaDTO obtenirFitxa(long idFitxa) throws StrategyException {
        try {
            return enllacQueryServiceDelegate.obtenirFitxa(idFitxa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public ProcedimentDTO obtenirProcediment(long idProcediment) throws StrategyException {
        try {
            return enllacQueryServiceDelegate.obtenirProcediment(idProcediment);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		this.setUrl(url);
	}

}
