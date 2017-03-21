package es.caib.rolsac.api.v2.catalegDocuments;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.catalegDocuments.ejb.CatalegDocumentsQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;

public class CatalegDocumentsQueryServiceAdapter extends CatalegDocumentsDTO implements CatalegDocumentsQueryService {

	private static final long serialVersionUID = 4848186814788719338L;

	private CatalegDocumentsQueryServiceStrategy catalegDocumentsQueryServiceStrategy;	
	
	private String rolsacUrl;
	public void setRolsacUrl(String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;
		if (rolsacUrl != null && catalegDocumentsQueryServiceStrategy != null) {
			catalegDocumentsQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}
	
    public void setCatalegDocumentsQueryServiceStrategy(CatalegDocumentsQueryServiceStrategy catalegDocumentsQueryServiceStrategy) {
        this.catalegDocumentsQueryServiceStrategy = catalegDocumentsQueryServiceStrategy;
    }	
	
    private STRATEGY getStrategy() {
        return catalegDocumentsQueryServiceStrategy instanceof CatalegDocumentsQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }    
    
	public CatalegDocumentsQueryServiceAdapter(CatalegDocumentsDTO dto) throws QueryServiceException {
		try {
			PropertyUtils.copyProperties(this, dto);
		} catch (Exception e) {
			throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
		}
	}
	
	public int getNumDocumentsTramit() throws QueryServiceException {
		try {
			return catalegDocumentsQueryServiceStrategy.getNumDocumentsTramit(getId());	
		} catch (StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "número de documentos trámite.", e);
		}
	}

	public List<DocumentTramitQueryServiceAdapter> llistarDocumentsTramits(DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException {
				
		try {
			List<DocumentTramitDTO> llistaDTO = catalegDocumentsQueryServiceStrategy.llistarDocumentsTramits(getId(), documentTramitCriteria);
			List<DocumentTramitQueryServiceAdapter> llistaDocumentTramitQueryServiceAdapter = new ArrayList<DocumentTramitQueryServiceAdapter>();
			
			for ( DocumentTramitDTO documentTramitDTO : llistaDTO ) {
				DocumentTramitQueryServiceAdapter dqsa = (DocumentTramitQueryServiceAdapter) BeanUtils
						.getAdapter("documentTramit", getStrategy(),
								documentTramitDTO);
				if (dqsa != null && rolsacUrl != null) {
					dqsa.setRolsacUrl(rolsacUrl);
				}
				llistaDocumentTramitQueryServiceAdapter
						.add(dqsa);
			}

			return llistaDocumentTramitQueryServiceAdapter;
		} catch (StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos trámites.", e);
		}
	}

	
	
}
