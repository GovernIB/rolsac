package es.caib.rolsac.api.v2.documentoNormativa.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.documentoNormativa.DocumentoNormativaQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;

public class DocumentoNormativaQueryServiceWSStrategy implements DocumentoNormativaQueryServiceStrategy {

    DocumentoNormativaQueryServiceGateway gateway;

    public void setGateway(DocumentoNormativaQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    
    public void setUrl(String url) {
    	if (this.gateway != null) {
    		this.gateway.setUrl(url);
    	}
	}
    
    public NormativaDTO obtenirNormativa(long id) throws StrategyException {
    	try {
        	return gateway.obtenirNormativa(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) throws StrategyException {
    	try {
        	return gateway.obtenirArxiu(idArxiu);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }


	
}
