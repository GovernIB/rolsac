package es.caib.rolsac.api.v2.idioma;

import java.util.List;

import es.caib.rolsac.api.v2.exception.QueryServiceException;

public interface IdiomaQueryService {

	public List<IdiomaQueryServiceAdapter> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws QueryServiceException;

}
