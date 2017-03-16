package es.caib.rolsac.api.v2.butlleti;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public interface ButlletiQueryServiceStrategy {

    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException;
    
    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws StrategyException;

	public void setUrl(String url);

}
