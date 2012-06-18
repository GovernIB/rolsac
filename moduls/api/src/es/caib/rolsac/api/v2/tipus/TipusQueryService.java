package es.caib.rolsac.api.v2.tipus;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;


public interface TipusQueryService {
    
    public int getNumNormatives();
    
    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria);

}
