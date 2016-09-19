package es.caib.rolsac.api.v2.silencio;

import es.caib.rolsac.api.v2.exception.QueryServiceException;

public interface SilencioQueryService {
	
	public SilencioQueryServiceAdapter obtenirSilencio(String codSilencio, String idioma) throws QueryServiceException;

}
