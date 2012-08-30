package es.caib.rolsac.api.v2.taxa.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceWSStrategy implements TaxaQueryServiceStrategy {

	TaxaQueryServiceGateway gateway;
	
	public void setGateway(TaxaQueryServiceGateway gateway) {
		this.gateway = gateway;
	}
	
    public TramitDTO obtenirTramit(long id) throws StrategyException {
    	try {
    		return gateway.obtenirTramit(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }
}
