package es.caib.rolsac.api.v2.edifici.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.edifici.EdificiQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class EdificiQueryServiceWSStrategy implements EdificiQueryServiceStrategy {

	EdificiQueryServiceGateway gateway;
	
	public void setGateway(EdificiQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
    public int getNumUnitatsAdministratives(long id) throws StrategyException {
    	try {
    		return gateway.getNumUnitatsAdministratives(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirFotoPequenya(Long idFotoPequenya) throws StrategyException {
    	try {
    		return gateway.obtenirFotoPequenya(idFotoPequenya);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirFotoGrande(Long idFotoGrande) throws StrategyException {
    	try {
    		return gateway.obtenirFotoGrande(idFotoGrande);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirPlano(Long idPlano) throws StrategyException {
    	try {
    		return gateway.obtenirPlano(idPlano);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}    	
    }

    public List<UnitatAdministrativaDTO> llistarUnitatsAdministratives(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) throws StrategyException {
    	try {
    		return gateway.llistarUnitatsAdministratives(id, unitatAdministrativaCriteria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	} catch (APIException e) {
    		throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
    	}
    }
    
}
