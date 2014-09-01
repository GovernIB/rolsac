package es.caib.rolsac.api.v2.tipus.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceStrategy;

public class TipusQueryServiceWSStrategy implements TipusQueryServiceStrategy {

	TipusQueryServiceGateway gateway;

	public void setGateway(TipusQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public List<NormativaDTO> llistarNormatives(Long id,
			NormativaCriteria normativaCriteria) throws StrategyException {

		try {
			return gateway.llistarNormatives(id, normativaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public int getNumNormatives(Long id, TIPUS_NORMATIVA totes)
			throws StrategyException {
		try {
			return gateway.getNumNormatives(id, totes);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
		
	}
}
