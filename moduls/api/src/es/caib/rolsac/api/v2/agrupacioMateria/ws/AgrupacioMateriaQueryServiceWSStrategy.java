package es.caib.rolsac.api.v2.agrupacioMateria.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.seccio.SeccioDTO;

public class AgrupacioMateriaQueryServiceWSStrategy implements
		AgrupacioMateriaQueryServiceStrategy {

	AgrupacioMateriaQueryServiceGateway gateway;

	public void setGateway(AgrupacioMateriaQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public void setUrl(String url) {
		if (this.gateway  != null) {
			this.gateway.setUrl(url);
		}
	}	


	public int getNumMateries(long id) throws StrategyException {
    	try {
			return gateway.getNumMateries(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}
	}
	
	public SeccioDTO obtenirSeccio(long idSeccio) throws StrategyException {		
		try {
			return gateway.obtenirSeccio(idSeccio);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<MateriaDTO> llistarMateries(long id,
			MateriaCriteria materiaCriteria) throws StrategyException {

		try { 
			return gateway.llistarMateries(id, materiaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

}
