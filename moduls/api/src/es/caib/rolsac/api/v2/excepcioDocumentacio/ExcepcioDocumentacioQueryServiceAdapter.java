package es.caib.rolsac.api.v2.excepcioDocumentacio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsCriteria;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitCriteria;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitDTO;
import es.caib.rolsac.api.v2.documentTramit.DocumentTramitQueryServiceAdapter;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ejb.ExcepcioDocumentacioQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;

public class ExcepcioDocumentacioQueryServiceAdapter extends
		ExcepcioDocumentacioDTO implements ExcepcioDocumentacioQueryService {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4672378134698424478L;
	private ExcepcioDocumentacioQueryServiceStrategy excepcioDocumentacioQueryServiceStrategy;

    public void setExcepcioDocumentacioQueryServiceStrategy(ExcepcioDocumentacioQueryServiceStrategy excepcioDocumentacioQueryServiceStrategy) {
        this.excepcioDocumentacioQueryServiceStrategy = excepcioDocumentacioQueryServiceStrategy;
    }
    

    private String rolsacUrl;
	public void setRolsacUrl(String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;
		if (excepcioDocumentacioQueryServiceStrategy != null) {
			excepcioDocumentacioQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    public ExcepcioDocumentacioQueryServiceAdapter(ExcepcioDocumentacioDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    private STRATEGY getStrategy() {
        return excepcioDocumentacioQueryServiceStrategy instanceof ExcepcioDocumentacioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
	    
	public int getNumCatalegsDocuments() throws QueryServiceException {
		try {
			return excepcioDocumentacioQueryServiceStrategy.getNumCatalegsDocuments(getId());
		} catch (StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "número de catálogos de documentos.", e);
		}
	}

	public int getNumDocumentsTramit() throws QueryServiceException {
		try {
			return excepcioDocumentacioQueryServiceStrategy.getNumDocumentsTramit(getId());
		} catch (StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "número de documentos trámite.", e);			
		}
	}

	public List<CatalegDocumentsQueryServiceAdapter> llistarCatalegsDocuments(
			CatalegDocumentsCriteria catalegDocumentsCriteria)
			throws QueryServiceException {
		try {
			List<CatalegDocumentsDTO> llistaDTO = excepcioDocumentacioQueryServiceStrategy
					.llistarCatalegsDocuments(getId(), catalegDocumentsCriteria);
			List<CatalegDocumentsQueryServiceAdapter> llistaCatalegDocumentsQueryServiceAdapter = new ArrayList<CatalegDocumentsQueryServiceAdapter>();
			
			for ( CatalegDocumentsDTO catalegDocumentsDTO : llistaDTO ) {
				CatalegDocumentsQueryServiceAdapter cqsa = (CatalegDocumentsQueryServiceAdapter) BeanUtils
				.getAdapter("catalegDocuments", getStrategy(),
						catalegDocumentsDTO);
				if (cqsa != null && rolsacUrl != null) {
					cqsa.setRolsacUrl(rolsacUrl);
				}
				llistaCatalegDocumentsQueryServiceAdapter
						.add(cqsa);
			}
			
			return llistaCatalegDocumentsQueryServiceAdapter;
		} catch (StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "catálogos documentos", e);
		}
	}
	
	public List<DocumentTramitQueryServiceAdapter> llistarDocumentsTramit(
			DocumentTramitCriteria documentTramitCriteria)
			throws QueryServiceException {
		
		try {
			List<DocumentTramitDTO> llistaDTO = excepcioDocumentacioQueryServiceStrategy.llistarDocumentsTramit(getId(), documentTramitCriteria);
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
			throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "documentos trámite.", e);
		}
	}

}