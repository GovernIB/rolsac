package es.caib.rolsac.api.v2.idioma;

import java.util.List;

import es.caib.rolsac.api.v2.exception.StrategyException;

public interface IdiomaQueryServiceStrategy {

	public List<IdiomaDTO> llistarIdiomes(IdiomaCriteria idiomaCriteria) throws StrategyException;

}
