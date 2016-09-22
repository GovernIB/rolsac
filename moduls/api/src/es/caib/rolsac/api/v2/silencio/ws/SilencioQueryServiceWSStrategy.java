package es.caib.rolsac.api.v2.silencio.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;
import es.caib.rolsac.api.v2.silencio.SilencioQueryServiceStrategy;


public class SilencioQueryServiceWSStrategy implements SilencioQueryServiceStrategy
{
	SilencioQueryServiceGateway gateway;
	
	public void setGateway( SilencioQueryServiceGateway gateway ) {
    	this.gateway = gateway;
    }
	
	public SilencioDTO obtenirSilenci(Long silencio, String idioma) throws StrategyException
	{
		try {
			return gateway.obtenirSilenci(silencio, idioma);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
	
}
