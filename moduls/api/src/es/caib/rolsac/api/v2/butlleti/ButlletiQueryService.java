package es.caib.rolsac.api.v2.butlleti;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;

public interface ButlletiQueryService {

    public int getNumNormatives();
    
    public int getNumNormativesLocals();
    
    public int getNumNormativesExternes();
    
    List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria);

}
