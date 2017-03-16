package es.caib.rolsac.api.v2.espaiTerritorial.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialCriteria;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialDTO;
import es.caib.rolsac.api.v2.espaiTerritorial.EspaiTerritorialQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EspaiTerritorialQueryServiceWSStrategy implements
		EspaiTerritorialQueryServiceStrategy {

	EspaiTerritorialQueryServiceGateway gateway;

	public void setGateway(EspaiTerritorialQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}

	public int getNumFills(long id) throws StrategyException {
		try {
			return gateway.getNumFills(id);
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

	public List<EspaiTerritorialDTO> llistarFills(long id,
			EspaiTerritorialCriteria espaiTerritorialCriteria)
			throws StrategyException {
		try {
			return gateway.llistarFills(id, espaiTerritorialCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
			UnitatAdministrativaCriteria unitatAdministrativaCriteria)
			throws StrategyException {
		try {
			return gateway.llistarUnitatsAdministratives(id,
					unitatAdministrativaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public ArxiuDTO obtenirMapa(Long idMapa) throws StrategyException {
    	try {
    		return gateway.obtenirMapa(idMapa);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
	}

	public ArxiuDTO obtenirLogo(Long idLogo) throws StrategyException {
    	try {
    		return gateway.obtenirLogo(idLogo);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
	}

	public EspaiTerritorialDTO obtenirPare(Long idPadre) throws StrategyException {
		try {
			return gateway.obtenirPare(idPadre);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	
}
