package es.caib.rolsac.api.v2.tipus;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;


public interface TipusQueryServiceStrategy {

    List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria);

    int getNumNormatives(Long id, TIPUS_NORMATIVA totes);

}
