package es.caib.rolsac.api.v2.materiaAgrupacio;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public interface MateriaAgrupacioQueryServiceStrategy {

    public MateriaDTO obtenirMateria(Long idMateria) throws StrategyException;

    public AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion) throws StrategyException;

}
