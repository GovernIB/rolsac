package es.caib.rolsac.api.v2.silencio;

import es.caib.rolsac.api.v2.exception.StrategyException;

public interface SilencioQueryServiceStrategy {
	
	public SilencioDTO obtenirSilenci(Long codSilencio, String idioma) throws StrategyException;

}
