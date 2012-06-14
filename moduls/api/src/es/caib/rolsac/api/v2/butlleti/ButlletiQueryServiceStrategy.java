package es.caib.rolsac.api.v2.butlleti;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public interface ButlletiQueryServiceStrategy {

    List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria);
    
    int getNumNormatives(long id, TIPUS_NORMATIVA tipus);

}
