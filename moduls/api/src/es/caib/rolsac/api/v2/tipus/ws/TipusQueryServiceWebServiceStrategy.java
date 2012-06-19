package es.caib.rolsac.api.v2.tipus.ws;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceStrategy;

public class TipusQueryServiceWebServiceStrategy implements TipusQueryServiceStrategy{

    public List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

    public int getNumNormatives(Long id, TIPUS_NORMATIVA totes) {
        // TODO Auto-generated method stub
        return 0;
    }



}
