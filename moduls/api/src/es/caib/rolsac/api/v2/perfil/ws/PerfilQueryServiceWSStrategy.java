package es.caib.rolsac.api.v2.perfil.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaCriteria;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaDTO;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaCriteria;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilQueryServiceStrategy;

public class PerfilQueryServiceWSStrategy implements PerfilQueryServiceStrategy {

	PerfilQueryServiceGateway gateway;

	public void setGateway(PerfilQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}

	public int getNumIconesFamilia(long id) throws StrategyException {
		try {
			return gateway.getNumIconesFamilia(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public int getNumIconesMateria(long id) throws StrategyException {
		try {
			return gateway.getNumIconesMateria(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public List<IconaFamiliaDTO> llistarIconesFamilia(long id,
			IconaFamiliaCriteria iconaFamiliaCriteria) throws StrategyException {
		try {
			return gateway.llistarIconesFamilia(id, iconaFamiliaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}	
		}

	public List<IconaMateriaDTO> llistarIconesMateria(long id,
			IconaMateriaCriteria iconaMateriaCriteria) throws StrategyException {
		try {
			return gateway.llistarIconesMateria(id, iconaMateriaCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	
}
