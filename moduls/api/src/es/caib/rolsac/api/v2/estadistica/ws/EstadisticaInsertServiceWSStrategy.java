package es.caib.rolsac.api.v2.estadistica.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.estadistica.EstadisticaInsertServiceStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class EstadisticaInsertServiceWSStrategy implements EstadisticaInsertServiceStrategy {

	EstadisticaInsertServiceGateway gateway;	

	public void setGateway(EstadisticaInsertServiceGateway gateway) {
		this.gateway = gateway;
	}

	
	public boolean gravarEstadisticaFitxa(long fitxaId)
			throws StrategyException {
		
		try {
			return gateway.gravarEstadisticaFitxa(fitxaId);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
		
	}

	public boolean gravarEstadisticaFitxaPerMateria(long fitxaId, long materiaId)
			throws StrategyException {
		try {
			return gateway.gravarEstadisticaFitxaPerMateria(fitxaId, materiaId);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public boolean gravarEstadisticaFitxaPerUA(long fitxaId, long uaId)
			throws StrategyException {
		try {
			return gateway.gravarEstadisticaFitxaPerUA(fitxaId, uaId);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public boolean gravarEstadisticaMateria(long materiaId)
			throws StrategyException {
		try {
			return gateway.gravarEstadisticaMateria(materiaId);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public boolean gravarEstadisticaNormativa(long normativaId)
			throws StrategyException {
		try {
			return gateway.gravarEstadisticaNormativa(normativaId);					
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public boolean gravarEstadisticaProcediment(long procedimentId)
			throws StrategyException {
		try {
			return gateway.gravarEstadisticaProcediment(procedimentId);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}	}

	public boolean gravarEstadisticaUnitatAdministrativa(long uaId)
			throws StrategyException {
		try {
			return gateway.gravarEstadisticaUnitatAdministrativa(uaId);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}
}
