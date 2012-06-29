package es.caib.rolsac.api.v2.documentTramit;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
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

    public DocumentTramitQueryServiceAdapter(DocumentTramitDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return documentTramitQueryServiceStrategy instanceof DocumentTramitQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
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
            return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
        } catch (StrategyException e) {
             throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tramite.", e);
        }
    }
    
}
