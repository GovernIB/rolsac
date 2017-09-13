package es.caib.rolsac.api.v2.butlleti;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaQueryServiceAdapter;

public interface ButlletiQueryService {

    public int getNumNormatives() throws QueryServiceException;
    
    @Deprecated
    public int getNumNormativesLocals() throws QueryServiceException;
    
    @Deprecated
    public int getNumNormativesExternes() throws QueryServiceException;
    
    public List<NormativaQueryServiceAdapter> llistarNormatives(NormativaCriteria normativaCriteria) throws QueryServiceException;

}
