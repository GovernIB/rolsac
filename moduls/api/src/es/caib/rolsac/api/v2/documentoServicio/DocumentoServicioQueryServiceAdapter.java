package es.caib.rolsac.api.v2.documentoServicio;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.documentoServicio.ejb.DocumentoServicioQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;

public class DocumentoServicioQueryServiceAdapter extends DocumentoServicioDTO implements DocumentoServicioQueryService {

    private static final long serialVersionUID = -411018636621900165L;

    private DocumentoServicioQueryServiceStrategy DocumentoServicioQueryServiceStrategy;

    public void setDocumentoServicioQueryServiceStrategy(DocumentoServicioQueryServiceStrategy DocumentoServicioQueryServiceStrategy) {
        this.DocumentoServicioQueryServiceStrategy = DocumentoServicioQueryServiceStrategy;
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
		if ( this.DocumentoServicioQueryServiceStrategy != null) {
			this.DocumentoServicioQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    public DocumentoServicioQueryServiceAdapter(DocumentoServicioDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return DocumentoServicioQueryServiceStrategy instanceof DocumentoServicioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
  /*  public NormativaQueryService obtenirNormativa() throws QueryServiceException {
        if (this.getNormativa() == null) {return null;}
        try {
            NormativaDTO dto = DocumentoNormativaQueryServiceStrategy.obtenirNormativa(this.getNormativa());
            return new NormativaQueryServiceAdapter(dto);
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "normativa.", e);
        }
    }*/

    public ArxiuQueryService obtenirArxiu() throws QueryServiceException {
        if (this.getArchivo() == null) {return null;}
        try {
            ArxiuDTO dto = DocumentoServicioQueryServiceStrategy.obtenirArxiu(this.getArchivo());
            ArxiuQueryServiceAdapter aqsa = (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
            if (aqsa != null && rolsacUrl != null) {
            	aqsa.setRolsacUrl(rolsacUrl);
            }
            return aqsa;
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "servicio.", e);
        }
    }

	
}
