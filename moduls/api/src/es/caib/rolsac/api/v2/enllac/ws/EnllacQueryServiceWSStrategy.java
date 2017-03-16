package es.caib.rolsac.api.v2.enllac.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.enllac.EnllacQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class EnllacQueryServiceWSStrategy implements EnllacQueryServiceStrategy {

    EnllacQueryServiceGateway gateway;

    public void setGateway( EnllacQueryServiceGateway gateway ) {
    	this.gateway = gateway;
    }
    
    public FitxaDTO obtenirFitxa(long id)  throws StrategyException {
    	try {
    		return gateway.obtenirFitxa(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e); 
    	}
    }

    public ProcedimentDTO obtenirProcediment(long id) throws StrategyException {
    	try {
    		return gateway.obtenirProcediment(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

	public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}


}
