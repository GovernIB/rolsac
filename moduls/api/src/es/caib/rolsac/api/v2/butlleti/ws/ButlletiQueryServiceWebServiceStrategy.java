package es.caib.rolsac.api.v2.butlleti.ws;

import java.util.List;

import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceStrategy;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceWebServiceStrategy implements ButlletiQueryServiceStrategy {

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) {
        // TODO Auto-generated method stub
        return 0;
    }

}
