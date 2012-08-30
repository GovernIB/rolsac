package es.caib.rolsac.api.v2.iconaMateria.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iconaMateria.IconaMateriaQueryServiceStrategy;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaMateriaQueryServiceWSStrategy implements IconaMateriaQueryServiceStrategy {

    IconaMateriaQueryServiceGateway gateway;

    public void setGateway(IconaMateriaQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    
    public MateriaDTO obtenirMateria(long id) throws StrategyException {
    	try {
    		return gateway.obtenirMateria(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public PerfilDTO obtenirPerfil(long id) throws StrategyException {
    	try {
    		return gateway.obtenirPerfil(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);    		
    	}
    }

    public ArxiuDTO obtenirIcona(long id) throws StrategyException {
    	try {
    		return gateway.obtenirIcona(id);
    	} catch (RemoteException e) {
    		throw new StrategyException( ExceptionMessages.REMOTE_CALL, e );
    	}
    }

}
