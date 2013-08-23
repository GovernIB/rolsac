package es.caib.rolsac.api.v2.iniciacio.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.iniciacio.IniciacioQueryServiceStrategy;


public class IniciacioQueryServiceWSStrategy implements IniciacioQueryServiceStrategy
{
	IniciacioQueryServiceGateway gateway;
	
	public void setGateway( IniciacioQueryServiceGateway gateway ) {
    	this.gateway = gateway;
    }
	
	public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws StrategyException
	{
		try {
			return gateway.llistarTipusIniciacions(iniciacioCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
	
	public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws StrategyException
	{
		try {
			return gateway.obtenirTipusIniciacio(iniciacioCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
	
}
