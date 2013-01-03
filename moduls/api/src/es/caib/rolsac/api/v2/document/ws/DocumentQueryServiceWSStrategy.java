package es.caib.rolsac.api.v2.document.ws;

import java.rmi.RemoteException;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.document.DocumentQueryServiceStrategy;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;

public class DocumentQueryServiceWSStrategy implements DocumentQueryServiceStrategy {

    DocumentQueryServiceGateway gateway;
    
    public void setGateway(DocumentQueryServiceGateway gateway) {
    	this.gateway = gateway;
    }

    public FitxaDTO obtenirFitxa(long id) throws StrategyException {
    	try {
    		return gateway.obtenirFitxa(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
    	}
    }

    public ProcedimentDTO obtenirProcediment(long id) throws StrategyException {
    	try {
    		return gateway.obtenirProcediment(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);    		
    	}
    }

    public ArxiuDTO obtenirArxiu(long id) throws StrategyException {
    	try {
    		return gateway.obtenirArxiu(id);
    	} catch (RemoteException e) {
    		throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);    		
    	}
    	
    }
}
