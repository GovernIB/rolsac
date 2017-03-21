package es.caib.rolsac.api.v2.documentTramit;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsDTO;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryService;
import es.caib.rolsac.api.v2.catalegDocuments.CatalegDocumentsQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentTramit.ejb.DocumentTramitQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioDTO;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioQueryService;
import es.caib.rolsac.api.v2.excepcioDocumentacio.ExcepcioDocumentacioQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class DocumentTramitQueryServiceAdapter extends DocumentTramitDTO implements DocumentTramitQueryService {

    private static final long serialVersionUID = -411018636621900165L;

    private DocumentTramitQueryServiceStrategy documentTramitQueryServiceStrategy;

    public void setDocumentTramitQueryServiceStrategy(DocumentTramitQueryServiceStrategy documentTramitQueryServiceStrategy) {
        this.documentTramitQueryServiceStrategy = documentTramitQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
		if ( this.documentTramitQueryServiceStrategy != null) {
			this.documentTramitQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    public DocumentTramitQueryServiceAdapter(DocumentTramitDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return documentTramitQueryServiceStrategy instanceof DocumentTramitQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public TramitQueryService obtenirTramit() throws QueryServiceException {
        if (this.getTramit() == null) {return null;}
        try {
            TramitDTO dto = documentTramitQueryServiceStrategy.obtenirTramit(this.getTramit());
            return new TramitQueryServiceAdapter(dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tramite.", e);
        }
    }

    public ArxiuQueryService obtenirArxiu() throws QueryServiceException {
        if (this.getArchivo() == null) {return null;}
        try {
            ArxiuDTO dto = documentTramitQueryServiceStrategy.obtenirArxiu(this.getArchivo());
            ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (aqsa != null && rolsacUrl != null) {
            	aqsa.setRolsacUrl(rolsacUrl);
            }
            return aqsa;
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tramite.", e);
        }
    }

	public CatalegDocumentsQueryService obtenirCatalegDocument() throws QueryServiceException {
		
		if (this.getDocCatalogo() == null) { return null; }
		
		try {
			CatalegDocumentsDTO dto = documentTramitQueryServiceStrategy.obtenirCatalegDocuments(this.getDocCatalogo());
			CatalegDocumentsQueryServiceAdapter aqsa = (CatalegDocumentsQueryServiceAdapter) BeanUtils.getAdapter("catalegDocuments", getStrategy(), dto);
			if (aqsa != null && rolsacUrl != null) {
            	aqsa.setRolsacUrl(rolsacUrl);
            }
			return aqsa;
		} catch (StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "catálogo documentos.", e);
		}
	}

	public ExcepcioDocumentacioQueryService obtenirExcepcioDocumentacio()
			throws QueryServiceException {
		
		if (this.getExcepcioDocumentacio() == null) { return null; }
		
		try {
			ExcepcioDocumentacioDTO dto = documentTramitQueryServiceStrategy.obtenirExcepcioDocumentacio(this.getExcepcioDocumentacio());
			ExcepcioDocumentacioQueryServiceAdapter aqsa =(ExcepcioDocumentacioQueryServiceAdapter) BeanUtils.getAdapter("excepcioDocumentacio", getStrategy(), dto);
			if (aqsa != null && rolsacUrl != null) {
            	aqsa.setRolsacUrl(rolsacUrl);
            }
			return aqsa;
		} catch (StrategyException e) {
			throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "excepción documentación." , e);
		}
	}

	
}
