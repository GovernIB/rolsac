package es.caib.rolsac.api.v2.butlleti.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceStrategy;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceEJBStrategy implements ButlletiQueryServiceStrategy {

    private ButlletiQueryServiceDelegate butlletiQueryServiceDelegate;
    
    public void setButlletiQueryServiceDelegate(ButlletiQueryServiceDelegate butlletiQueryServiceDelegate) {
        this.butlletiQueryServiceDelegate = butlletiQueryServiceDelegate;
    }
    
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        return butlletiQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
    }

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) {
        return butlletiQueryServiceDelegate.getNumNormatives(id, tipus);
    }

}
