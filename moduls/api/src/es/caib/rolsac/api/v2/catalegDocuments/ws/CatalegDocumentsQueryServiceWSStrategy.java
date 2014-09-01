package es.caib.rolsac.api.v2.catalegDocuments.ws;

import java.rmi.RemoteException;
import java.util.List;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryServiceStrategy;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.exception.APIException;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.StrategyException;

public class CatalegDocumentsQueryServiceWSStrategy implements
		CatalegDocumentsQueryServiceStrategy {

	CatalegDocumentsQueryServiceGateway gateway;

	public void setGateway(CatalegDocumentsQueryServiceGateway gateway) {
		this.gateway = gateway;
	}	
	
	public int getNumDocumentsTramit(long id) throws StrategyException {		
		try {
			return gateway.getNumDocumentsTramit(id);
		} catch (RemoteException e) {
			throw new StrategyException(ExceptionMessages.REMOTE_CALL, e);			
		}	
	}

	public List<DocumentTramitDTO> llistarDocumentsTramits(long id,
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