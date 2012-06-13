package es.caib.rolsac.api.v2.butlleti;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService;

public interface ButlletiQueryService {

    // getNumNormatives
    
    List<NormativaQueryService> llistarNormatives(NormativaCriteria normativaCriteria);

}
