package es.caib.rolsac.api.v2.idioma.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.idioma.IdiomaCriteria;
import es.caib.rolsac.api.v2.idioma.IdiomaDTO;
import es.caib.rolsac.api.v2.idioma.IdiomaQueryServiceStrategy;

public class IdiomaQueryServiceWSStrategy implements IdiomaQueryServiceStrategy {

	IdiomaQueryServiceGateway gateway;

	public void setGateway(IdiomaQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws StrategyException {
		
		try {

			return gateway.llistarIdiomes(idiomaCriteria);
			
		} catch (RemoteException e) {
			
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
			
		} catch (APIException e) {
			
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
			
		}
		
	}
	
}
