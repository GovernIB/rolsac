package es.caib.rolsac.api.v2.materiaAgrupacio.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.materiaAgrupacio.MateriaAgrupacioQueryServiceStrategy;

public class MateriaAgrupacioQueryServiceWSStrategy implements MateriaAgrupacioQueryServiceStrategy {

    MateriaAgrupacioQueryServiceGateway gateway;

    public void setGateway(MateriaAgrupacioQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
  
    public MateriaDTO obtenirMateria(Long idMateria) throws StrategyException {
    	try {
    		return gateway.obtenirMateria(idMateria);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion) throws StrategyException {
    	try {
    		return gateway.obtenirAgrupacioMateria(idAgrupacion.longValue());
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

}
