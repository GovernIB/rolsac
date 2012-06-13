package es.caib.rolsac.api.v2.taxa;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.taxa.ejb.TaxaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class TaxaQueryServiceAdapter extends TaxaDTO implements TaxaQueryService {

    TaxaQueryServiceStrategy taxaQueryServiceStrategy;

    public TaxaQueryServiceAdapter() {
        // FIXME: don't harcode the TaxaQueryServiceEJBStrategy.
        taxaQueryServiceStrategy = new TaxaQueryServiceEJBStrategy();
    }
    
    public TaxaQueryServiceAdapter(TaxaDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    public TramitQueryService obtenirTramit(TramitCriteria tramitCriteria) {

        TramitDTO dto = taxaQueryServiceStrategy.obtenirTramit(id, tramitCriteria);
        return new TramitQueryServiceAdapter(dto);
    }

}
