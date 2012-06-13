package es.caib.rolsac.api.v2.butlleti;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;

public interface ButlletiQueryServiceStrategy {

    List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria);

}
