package es.caib.rolsac.api.v2.excepcioDocumentacio.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class ExcepcioDocumentacioQueryServiceWSStrategy implements ExcepcioDocumentacioQueryServiceStrategy {

	ExcepcioDocumentacioQueryServiceGateway gateway;
	
	public void setGateway(ExcepcioDocumentacioQueryServiceGateway gateway) {
		this.gateway = gateway;
	}		
	
	public int getNumCatalegsDocuments(long id) throws StrategyException {
		try {
			return gateway.getNumCatalegsDocuments(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}
	}

	public int getNumDocumentsTramit(long id) throws StrategyException {
		try {
			return gateway.getNumDocumentsTramit(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}
	}
	
	public List<CatalegDocumentsDTO> llistarCatalegsDocuments(long id,
			CatalegDocumentsCriteria catalegDocumentCriteria)
			throws StrategyException {
		
		try {
			return gateway.llistarCatalegsDocuments(id, catalegDocumentCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}

	public List<DocumentTramitDTO> llistarDocumentsTramit(long id,
			DocumentTramitCriteria documentTramitCriteria)
			throws StrategyException {
		
		try {
			return gateway.llistarDocumentsTramit(id, documentTramitCriteria);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);
		} catch (APIException e) {
			throw new StrategyException(ExceptionMessages.GATEWAY_OBJECT2DTO, e);
		}
	}
}
