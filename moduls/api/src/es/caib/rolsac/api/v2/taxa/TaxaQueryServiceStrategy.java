package es.caib.rolsac.api.v2.taxa;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public interface TaxaQueryServiceStrategy {

    public TramitDTO obtenirTramit(long idTramit) throws StrategyException;

	public void setUrl(String url);

}
