package es.caib.rolsac.api.v2.destinatari;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.formulari.FormulariQueryServiceAdapter;

public class DestinatariQueryServiceAdapter extends DestinatariDTO implements DestinatariQueryService {

    private static Log log = LogFactory.getLog(FormulariQueryServiceAdapter.class);
    
    public DestinatariQueryServiceAdapter(DestinatariDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            log.error("Error instanciando DestinatariQueryServiceAdapter.", e);
        }
    }

}
