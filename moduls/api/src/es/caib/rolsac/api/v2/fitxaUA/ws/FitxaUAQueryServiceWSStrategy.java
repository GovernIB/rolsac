package es.caib.rolsac.api.v2.fitxaUA.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.fitxaUA.FitxaUAQueryServiceStrategy;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class FitxaUAQueryServiceWSStrategy implements
		FitxaUAQueryServiceStrategy {

	FitxaUAQueryServiceGateway gateway;

	public void setGateway(FitxaUAQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public void setUrl(String url) {
		if (this.gateway  != null) {
			this.gateway.setUrl(url);
		}
	}

	public FitxaDTO obtenirFitxa(long idFitxa) throws StrategyException {
    	try {
			return gateway.obtenirFitxa(idFitxa);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}		
	}

	public SeccioDTO obtenirSeccio(long idSeccio) throws StrategyException {
    	try {
			return gateway.obtenirSeccio(idSeccio);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}		
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(
			long idUnitatAdministrativa) throws StrategyException {
    	try {
			return gateway.obtenirUnitatAdministrativa(idUnitatAdministrativa);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}		
		
	}

	
}