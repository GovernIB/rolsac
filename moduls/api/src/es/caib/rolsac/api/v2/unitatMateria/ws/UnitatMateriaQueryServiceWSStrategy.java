package es.caib.rolsac.api.v2.unitatMateria.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatMateria.UnitatMateriaQueryServiceStrategy;

public class UnitatMateriaQueryServiceWSStrategy implements
		UnitatMateriaQueryServiceStrategy {

	UnitatMateriaQueryServiceGateway gateway;

	public void setGateway(UnitatMateriaQueryServiceGateway gateway) throws StrategyException {
		this.gateway = gateway;
	}
	
	 public void setUrl(String url) {
			this.gateway.setUrl(url);
		}
	 
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) throws StrategyException {
    	try {
    		return gateway.obtenirUnitatAdministrativa(idUnitat);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public MateriaDTO obtenirMateria(Long idMateria) throws StrategyException {
    	try {
    		return gateway.obtenirMateria(idMateria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    	
    }

}
