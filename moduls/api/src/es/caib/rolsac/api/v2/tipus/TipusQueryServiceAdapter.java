package es.caib.rolsac.api.v2.tipus;

import org.apache.commons.beanutils.PropertyUtils;

public class TipusQueryServiceAdapter extends TipusDTO implements TipusQueryService {

    TipusQueryServiceStrategy taxaQueryServiceStrategy;

    public TipusQueryServiceAdapter(TipusDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
}
