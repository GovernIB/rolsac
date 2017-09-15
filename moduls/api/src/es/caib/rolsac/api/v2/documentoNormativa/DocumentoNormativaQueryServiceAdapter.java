package es.caib.rolsac.api.v2.documentoNormativa;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoNormativa.ejb.DocumentoNormativaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;

public class DocumentoNormativaQueryServiceAdapter extends DocumentoNormativaDTO implements DocumentoNormativaQueryService {

    private static final long serialVersionUID = -411018636621900165L;

    private DocumentoNormativaQueryServiceStrategy DocumentoNormativaQueryServiceStrategy;

    public void setDocumentoNormativaQueryServiceStrategy(DocumentoNormativaQueryServiceStrategy DocumentoNormativaQueryServiceStrategy) {
        this.DocumentoNormativaQueryServiceStrategy = DocumentoNormativaQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
		if ( this.DocumentoNormativaQueryServiceStrategy != null) {
			this.DocumentoNormativaQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    public DocumentoNormativaQueryServiceAdapter(DocumentoNormativaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return DocumentoNormativaQueryServiceStrategy instanceof DocumentoNormativaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public NormativaQueryService obtenirNormativa() throws QueryServiceException {
        if (this.getNormativa() == null) {return null;}
        try {
            NormativaDTO dto = DocumentoNormativaQueryServiceStrategy.obtenirNormativa(this.getNormativa());
            return new NormativaQueryServiceAdapter(dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "normativa.", e);
        }
    }

    public ArxiuQueryService obtenirArxiu() throws QueryServiceException {
        if (this.getArchivo() == null) {return null;}
        try {
            ArxiuDTO dto = DocumentoNormativaQueryServiceStrategy.obtenirArxiu(this.getArchivo());
            ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (aqsa != null && rolsacUrl != null) {
            	aqsa.setRolsacUrl(rolsacUrl);
            }
            return aqsa;
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "normativa.", e);
        }
    }

	
}
