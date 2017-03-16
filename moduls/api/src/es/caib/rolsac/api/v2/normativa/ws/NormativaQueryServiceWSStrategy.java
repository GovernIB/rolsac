package es.caib.rolsac.api.v2.normativa.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.afectacio.AfectacioDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.butlleti.ButlletiDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class NormativaQueryServiceWSStrategy implements
		NormativaQueryServiceStrategy {

	NormativaQueryServiceGateway gateway;

	public void setGateway(NormativaQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public void setUrl(String url) {
		if (this.gateway  != null) {
			this.gateway.setUrl(url);
		}
	}
	
	public int getNumAfectades(long id) throws StrategyException {
		try {
			return gateway.getNumAfectades(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumAfectants(long id) throws StrategyException {
		try {
			return gateway.getNumAfectants(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumProcediments(long id) throws StrategyException {
		try {
			return gateway.getNumProcediments(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ButlletiDTO obtenirButlleti(long idButlleti)
			throws StrategyException {
		try {
			return gateway.obtenirButlleti(idButlleti);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUniAdm)
			throws StrategyException {
		try {
			return gateway.obtenirUnitatAdministrativa(idUniAdm);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<NormativaDTO> llistarAfectades(long id)
			throws StrategyException {
		try {
			return gateway.llistarAfectades(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<NormativaDTO> llistarAfectants(long id)
			throws StrategyException {
		try {
			return gateway.llistarAfectants(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<ProcedimentDTO> llistarProcediments(long id,
			ProcedimentCriteria procedimentCriteria) throws StrategyException {
		try {
			return gateway.llistarProcediments(id, procedimentCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public ArxiuDTO obtenirArxiuNormativa(Long idArchivo)
			throws StrategyException {
		try {
			return gateway.obtenirArxiuNormativa(idArchivo);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public List<AfectacioDTO> llistarAfectacionsAfectants(Long id)
			throws StrategyException {
		try {
			return gateway.llistarAfectacionsAfectants(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public List<AfectacioDTO> llistarAfectacionsAfectades(Long id)
			throws StrategyException {
		try {
			return gateway.llistarAfectacionsAfectades(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	
}
