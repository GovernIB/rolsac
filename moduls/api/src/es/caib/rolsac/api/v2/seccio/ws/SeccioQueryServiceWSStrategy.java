package es.caib.rolsac.api.v2.seccio.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioCriteria;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;
import es.caib.rolsac.api.v2.seccio.SeccioQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class SeccioQueryServiceWSStrategy implements SeccioQueryServiceStrategy {

	SeccioQueryServiceGateway gateway;

	public void setGateway(SeccioQueryServiceGateway gateway)
			throws StrategyException {
		this.gateway = gateway;
	}

	public int getNumFilles(long id) throws StrategyException {
		
		try {
			return gateway.getNumFilles(id);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumFitxes(long id) throws StrategyException {
		try {
			return gateway.getNumFitxes(id);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public int getNumPares(long id) throws StrategyException {
		try {
			return gateway.getNumPares(id);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public int getNumUnitatsAdministratives(long id) throws StrategyException {
		try {
			return gateway.getNumUnitatsAdministratives(id);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public SeccioDTO obtenirPare(Long padre) throws StrategyException {
		try {
			return gateway.obtenirPare(padre);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}
	
	public List<SeccioDTO> llistarPares(long id) throws StrategyException {
		try {
			return gateway.llistarPares(id);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public List<SeccioDTO> llistarFilles(long id, SeccioCriteria seccioCriteria)
			throws StrategyException {
		try {
			return gateway.llistarFilles(id, seccioCriteria);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public List<FitxaDTO> llistarFitxes(long id, FitxaCriteria fitxaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarFitxes(id, fitxaCriteria);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);		
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}
}
