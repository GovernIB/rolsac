package es.caib.rolsac.api.v2.documentTramit.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceWSStrategy implements DocumentTramitQueryServiceStrategy {

    DocumentTramitQueryServiceGateway gateway;

    public void setGateway(DocumentTramitQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    
    public TramitDTO obtenirTramit(long id) throws StrategyException {
    	try {
        	return gateway.obtenirTramit(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException {
    	try {
        	return gateway.obtenirArxiu(idArxiu);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }
}
