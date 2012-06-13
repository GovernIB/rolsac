package es.caib.rolsac.api.v2.butlleti;

import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.butlleti.ejb.ButlletiQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService;

public class ButlletiQueryServiceAdapter extends ButlletiDTO implements ButlletiQueryService {

    // @Injected
    ButlletiQueryServiceStrategy butlletiQueryServiceStrategy;
    
    public ButlletiQueryServiceAdapter() {
        // FIXME: don't harcode the ProcedimentQueryServiceEJBStrategy.
        butlletiQueryServiceStrategy = new ButlletiQueryServiceEJBStrategy();
    }
    
    public ButlletiQueryServiceAdapter(ButlletiDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    public List<NormativaQueryService> llistarNormatives(NormativaCriteria normativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
