package es.caib.rolsac.api.v2.documentTramit;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryService;
import es.caib.rolsac.api.v2.arxiu.ArxiuQueryServiceAdapter;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class DocumentTramitQueryServiceAdapter extends DocumentTramitDTO implements DocumentTramitQueryService {

    private DocumentTramitQueryServiceStrategy documentTramitQueryServiceStrategy;

    public void setDocumentTramitQueryServiceStrategy(DocumentTramitQueryServiceStrategy documentTramitQueryServiceStrategy) {
        this.documentTramitQueryServiceStrategy = documentTramitQueryServiceStrategy;
    }

    public DocumentTramitQueryServiceAdapter(DocumentTramitDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    private STRATEGY getStrategy() {
        return documentTramitQueryServiceStrategy instanceof DocumentTramitQueryServiceStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public TramitQueryService obtenirTramit() {
        if (this.getTramit() == null) {return null;}
        TramitDTO dto = documentTramitQueryServiceStrategy.obtenirTramit(this.getTramit());
        return new TramitQueryServiceAdapter(dto);
    }

    public ArxiuQueryService obtenirArxiu() {
        if (this.getArchivo() == null) {return null;}
        ArxiuDTO dto = documentTramitQueryServiceStrategy.obtenirArxiu(this.getArchivo());
        return (ArxiuQueryServiceAdapter) BeanUtils.getAdapter("arxiu", getStrategy(), dto);
    }
    
}
