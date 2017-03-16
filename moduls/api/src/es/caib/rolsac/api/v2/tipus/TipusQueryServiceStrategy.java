package es.caib.rolsac.api.v2.tipus;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;


public interface TipusQueryServiceStrategy {

    public List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria) throws StrategyException;

    public int getNumNormatives(Long id, TIPUS_NORMATIVA totes) throws StrategyException;

	public void setUrl(String url);

}
