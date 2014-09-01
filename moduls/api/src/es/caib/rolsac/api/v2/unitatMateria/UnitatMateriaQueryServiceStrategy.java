package es.caib.rolsac.api.v2.unitatMateria;

import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface UnitatMateriaQueryServiceStrategy {

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(Long idUnitat) throws StrategyException;

    public MateriaDTO obtenirMateria(Long idMateria) throws StrategyException;

}
