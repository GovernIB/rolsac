package es.caib.rolsac.api.v2.familia.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.servicio.ServicioCriteria;
import es.caib.rolsac.api.v2.servicio.ServicioDTO;

public class FamiliaQueryServiceWSStrategy implements
		FamiliaQueryServiceStrategy {

	FamiliaQueryServiceGateway gateway;

	public void setGateway(FamiliaQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}


	public int getNumProcedimentsLocals(long id) throws StrategyException {
		try {
			return gateway.getNumProcedimentsLocals(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
	
	public int getNumServicios(long id) throws StrategyException {
		try {
			return gateway.getNumServicios(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public int getNumIcones(long id) throws StrategyException {
		try {
			return gateway.getNumIcones(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}	
	
	public List<ProcedimentDTO> llistarProcedimentsLocals(long id,
			ProcedimentCriteria procedimentCriteria) throws StrategyException {
		try {
			return gateway.llistarProcedimentsLocals(id, procedimentCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}
	
	public List<ServicioDTO> llistarServicios(long id,
			ServicioCriteria servicioCriteria) throws StrategyException {
		try {
			return gateway.llistarServicios(id, servicioCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<IconaFamiliaDTO> llistarIcones(long id,
			IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {
		try {
			return gateway.llistarIcones(id, iconaFamiliaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

}
