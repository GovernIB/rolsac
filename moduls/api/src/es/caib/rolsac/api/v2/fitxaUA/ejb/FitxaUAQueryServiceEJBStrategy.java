package es.caib.rolsac.api.v2.fitxaUA.ejb;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceEJBStrategy implements FitxaUAQueryServiceStrategy {
    
    FitxaUAQueryServiceDelegate fitxaUAQueryServiceDelegate;    
    
    public void setFitxaUAQueryServiceDelegate(FitxaUAQueryServiceDelegate fitxaUAQueryServiceDelegate) {
        this.fitxaUAQueryServiceDelegate = fitxaUAQueryServiceDelegate;
    }
    
    public FitxaDTO obtenirFitxa(long idFitxa) throws StrategyException {
        try {
            return fitxaUAQueryServiceDelegate.obtenirFitxa(idFitxa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }
    public SeccioDTO obtenirSeccio(long idSeccio) throws StrategyException {
        try {
            return fitxaUAQueryServiceDelegate.obtenirSeccio(idSeccio);
        } catch (DelegateException e) {
             throw new StrategyException(e);
        }
    }
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUnitatAdministrativa) throws StrategyException {
        try {
            return fitxaUAQueryServiceDelegate.obtenirUnitatAdministrativa(idUnitatAdministrativa);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJB.
	}



}
