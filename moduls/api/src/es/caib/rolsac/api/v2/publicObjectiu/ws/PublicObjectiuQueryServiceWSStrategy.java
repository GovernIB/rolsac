package es.caib.rolsac.api.v2.publicObjectiu.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalCriteria;
import es.caib.rolsac.api.v2.agrupacioFetVital.AgrupacioFetVitalDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.publicObjectiu.PublicObjectiuQueryServiceStrategy;

public class PublicObjectiuQueryServiceWSStrategy implements
		PublicObjectiuQueryServiceStrategy {

	PublicObjectiuQueryServiceGateway gateway;

	public void setGateway(PublicObjectiuQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public int getNumAgrupacions(long id) throws StrategyException {
		try {
			return gateway.getNumAgrupacions(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<AgrupacioFetVitalDTO> llistarAgrupacions(long id,
			AgrupacioFetVitalCriteria agrupacioFetVitalCriteria)
			throws StrategyException {
		try {
			return gateway.llistarAgrupacions(id, agrupacioFetVitalCriteria);
		} catch (RemoteException e) {
			throw new  StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
}
