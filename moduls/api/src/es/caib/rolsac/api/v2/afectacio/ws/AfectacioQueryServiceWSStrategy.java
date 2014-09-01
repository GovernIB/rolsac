package es.caib.rolsac.api.v2.afectacio.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.afectacio.AfectacioQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.tipusAfectacio.TipusAfectacioDTO;

public class AfectacioQueryServiceWSStrategy implements AfectacioQueryServiceStrategy {

    AfectacioQueryServiceGateway gateway;

    public void setGateway(AfectacioQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    
	public NormativaDTO obtenirAfectant(long idAfectant)
			throws StrategyException {
		try {
			return gateway.obtenirAfectant(idAfectant);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}
	}

	public NormativaDTO obtenirNormativa(long idNormativa)
			throws StrategyException {

		try {
			return gateway.obtenirNormativa(idNormativa);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
			
	}

	public TipusAfectacioDTO obtenirTipusAfectacio(long idTipusAfectacio)
			throws StrategyException {
		try {
			return gateway.obtenirTipusAfectacio(idTipusAfectacio);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}
    
}