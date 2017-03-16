package es.caib.rolsac.api.v2.tramit.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.taxa.TaxaCriteria;
import es.caib.rolsac.api.v2.taxa.TaxaDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class TramitQueryServiceWSStrategy implements TramitQueryServiceStrategy {

	// @Injected
	TramitQueryServiceGateway gateway;

	public void setGateway(TramitQueryServiceGateway gateway) {
		this.gateway = gateway;
	}

	public List<DocumentTramitDTO> llistatDocumentsInformatius(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		try {
			return gateway.llistatDocumentsInformatius(id, documentTramitCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<TaxaDTO> llistarTaxes(long id, TaxaCriteria taxaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarTaxes(id, taxaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}		
	}

	public int getNumDocumentsInformatius(long id) throws StrategyException {
		try {
			return gateway.getNumDocumentsInformatius(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public int getNumFormularis(long id) throws StrategyException {
		try {
			return gateway.getNumFormularis(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public int getNumTaxes(long id) throws StrategyException {
		try {
			return gateway.getNumTaxes(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

//	public Date getDataCaducitat(long id) throws StrategyException {
//		try {
//			return gateway.getDataCaducitat(id);
//		} catch (RemoteException e) {
//			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
//		}		
//	}
//
//	public String getTitol(long id) throws StrategyException {
//		try {
//			return gateway.getTitol(id);
//		} catch (RemoteException e) {
//			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
//		}		
//	}

	public ProcedimentDTO obtenirProcediment(long id) throws StrategyException {
		try {
			return gateway.obtenirProcediment(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
		
	}

	public UnitatAdministrativaDTO obtenirOrganCompetent(long id)
			throws StrategyException {
		try {
			return gateway.obtenirOrganCompetent(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public List<DocumentTramitDTO> llistarFormularis(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		try {
			return gateway.llistarFormularis(id, documentTramitCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}
}
