package es.caib.rolsac.api.v2.personal.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceWSStrategy implements
		PersonalQueryServiceStrategy {

	PersonalQueryServiceGateway gateway;

	public void setGateway(PersonalQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA)
			throws StrategyException {

		try {
			return gateway.obtenirUnitatAdministrativa(idUA);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}

}
