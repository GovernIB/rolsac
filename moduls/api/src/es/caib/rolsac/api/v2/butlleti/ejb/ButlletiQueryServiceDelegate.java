package es.caib.rolsac.api.v2.butlleti.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceDelegate {

    private ButlletiQueryServiceEJBLocator butlletiQueryServiceLocator;

    public void setButlletiQueryServiceLocator(ButlletiQueryServiceEJBLocator butlletiQueryServiceLocator) {
        this.butlletiQueryServiceLocator = butlletiQueryServiceLocator;
    }
    
    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) {
        ButlletiQueryServiceEJB ejb = butlletiQueryServiceLocator.getButlletiQueryServiceEJB();
        return ejb.getNumNormatives(id, tipus);
    }
    
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        ButlletiQueryServiceEJB ejb = butlletiQueryServiceLocator.getButlletiQueryServiceEJB();
        return ejb.llistarNormatives(id, normativaCriteria);
    }
}
