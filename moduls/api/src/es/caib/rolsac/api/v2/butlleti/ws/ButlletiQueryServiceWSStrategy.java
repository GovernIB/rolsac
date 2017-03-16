package es.caib.rolsac.api.v2.butlleti.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceWSStrategy implements
		ButlletiQueryServiceStrategy {

	ButlletiQueryServiceGateway gateway;

	public void setGateway(ButlletiQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}

	public List<NormativaDTO> llistarNormatives(long id,
			NormativaCriteria normativaCriteria) throws StrategyException {
		try {
			return gateway.llistarNormatives(id, normativaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		} catch (APIException e) {
				throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}					
	}

	public int getNumNormatives(long id, TIPUS_NORMATIVA tipus)
			throws StrategyException {
		try {
			return gateway.getNumNormatives(id, tipus);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}	
	}

	

}
