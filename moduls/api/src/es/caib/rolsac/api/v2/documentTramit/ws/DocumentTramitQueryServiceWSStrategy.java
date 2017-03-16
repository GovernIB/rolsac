package es.caib.rolsac.api.v2.documentTramit.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceStrategy;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceWSStrategy implements DocumentTramitQueryServiceStrategy {

    DocumentTramitQueryServiceGateway gateway;

    public void setGateway(DocumentTramitQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }
    
    public void setUrl(String url) {
    	if (this.gateway != null) {
    		this.gateway.setUrl(url);
    	}
	}
    
    public TramitDTO obtenirTramit(long id) throws StrategyException {
    	try {
        	return gateway.obtenirTramit(id);
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

	public CatalegDocumentsDTO obtenirCatalegDocuments(long idCatalegDocuments)
			throws StrategyException {
		try {
			return gateway.obtenirCatalegDocuments(idCatalegDocuments);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}
	}

	public ExcepcioDocumentacioDTO obtenirExcepcioDocumentacio(
			long idExcepcioDocumentacio) throws StrategyException {
		try {
			return gateway.obtenirExcepcioDocumentacio(idExcepcioDocumentacio);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		}		
	}

	
}
