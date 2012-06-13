package es.caib.rolsac.api.v2.formulari;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.formulari.ejb.FormulariQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class FormulariQueryServiceAdapter extends FormulariDTO implements FormulariQueryService {

    FormulariQueryServiceStrategy formulariQueryServiceStrategy;

    public FormulariQueryServiceAdapter() {
        // FIXME: don't harcode the formulariQueryServiceEJBStrategy.
        formulariQueryServiceStrategy = new FormulariQueryServiceEJBStrategy();
    }
    
    public FormulariQueryServiceAdapter(FormulariDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public TramitQueryService obtenirTramit(TramitCriteria tramitCriteria) {
        TramitDTO dto = formulariQueryServiceStrategy.obtenirTramit(id, tramitCriteria);
        return new TramitQueryServiceAdapter(dto);
    }

    public String getTitol() {
        // TODO Auto-generated method stub
        return null;
    }

}
