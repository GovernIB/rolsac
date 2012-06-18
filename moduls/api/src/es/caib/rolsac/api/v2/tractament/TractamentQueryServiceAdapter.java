package es.caib.rolsac.api.v2.tractament;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceAdapter;

public class TractamentQueryServiceAdapter extends TractamentDTO implements TractamentQueryService {

    private static Log log = LogFactory.getLog(FormulariQueryServiceAdapter.class);
    
    public TractamentQueryServiceAdapter(TractamentDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
            log.error("Error instanciando TractamentQueryServiceAdapter.", e);
        }
    }

}
