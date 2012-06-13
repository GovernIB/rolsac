package es.caib.rolsac.api.v2.tractament;

import org.apache.commons.beanutils.PropertyUtils;

public class TractamentQueryServiceAdapter extends TractamentDTO implements TractamentQueryService {

    public TractamentQueryServiceAdapter(TractamentDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

}
