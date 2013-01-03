package es.caib.rolsac.api.v2.usuari.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.usuari.UsuariQueryServiceStrategy;

public class UsuariQueryServiceWSStrategy implements UsuariQueryServiceStrategy {

	UsuariQueryServiceGateway gateway;

	public void setGateway(UsuariQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws StrategyException {

		try {
			return gateway.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public int getNumUnitatsAdministratives(long id) throws StrategyException {
		try {
			return gateway.getNumUnitatsAdministratives(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
}