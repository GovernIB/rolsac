package es.caib.rolsac.api.v2.documentoServicio.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentoServicio.DocumentoServicioQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class DocumentoServicioQueryServiceWSStrategy implements DocumentoServicioQueryServiceStrategy {

    DocumentoServicioQueryServiceGateway gateway;

    public void setGateway(DocumentoServicioQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    
    public void setUrl(String url) {
    	if (this.gateway != null) {
    		this.gateway.setUrl(url);
    	}
	}
    
 /*   public NormativaDTO obtenirNormativa(long id) throws StrategyException {
    	try {
        	return gateway.obtenirNormativa(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }*/

    public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException {
    	try {
        	return gateway.obtenirArxiu(idArxiu);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }


	
}
