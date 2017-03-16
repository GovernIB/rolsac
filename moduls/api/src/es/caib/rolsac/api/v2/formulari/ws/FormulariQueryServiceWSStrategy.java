package es.caib.rolsac.api.v2.formulari.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceWSStrategy implements
		FormulariQueryServiceStrategy {

	//@Injected
    FormulariQueryServiceGateway gateway;

    public void setGateway(FormulariQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    	
	public ArxiuDTO obtenirArchivo(Long archivo) throws StrategyException {
		try {
			return gateway.obtenirArchivo(archivo);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	public ArxiuDTO obtenirManual(Long manual) throws StrategyException {
		try {
			return gateway.obtenirManual(manual);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public TramitDTO obtenirTramit(Long idTramit) throws StrategyException {
		try {
			return gateway.obtenirTramit(idTramit);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public void setUrl(String url) {
		if (this.gateway  != null) {
			this.gateway.setUrl(url);
		}
	}
}

