package es.caib.rolsac.api.v2.iconaFamilia.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.familia.FamiliaDTO;
import es.caib.rolsac.api.v2.iconaFamilia.IconaFamiliaQueryServiceStrategy;
import es.caib.rolsac.api.v2.perfil.PerfilDTO;

public class IconaFamiliaQueryServiceWSStrategy implements IconaFamiliaQueryServiceStrategy {

    IconaFamiliaQueryServiceGateway gateway;
    
    public void setGateway( IconaFamiliaQueryServiceGateway gateway ) {
    	this.gateway = gateway;
    }
    
    public void setUrl(String url) {
		if (this.gateway != null) {
			this.gateway.setUrl(url);
		}
	}

    public FamiliaDTO obtenirFamilia(long id) throws StrategyException {
    	try {
    		return gateway.obtenirFamilia(id);
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
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

	
}
