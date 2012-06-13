package es.caib.rolsac.api.v2.tipusAfectacio;

import org.apache.commons.beanutils.PropertyUtils;

public class TipusAfectacioQueryServiceAdapter extends TipusAfectacioDTO implements TipusAfectacioQueryService {

    // @Injected
    TipusAfectacioQueryServiceStrategy tipusAfectacioQueryServiceStrategy;

    public TipusAfectacioQueryServiceAdapter() {
        // FIXME: don't harcode the TramitQueryServiceEJBStrategy.
    }
    
    public TipusAfectacioQueryServiceAdapter(TipusAfectacioDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

}
