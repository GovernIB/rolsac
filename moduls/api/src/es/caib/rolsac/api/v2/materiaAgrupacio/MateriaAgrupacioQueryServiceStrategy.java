package es.caib.rolsac.api.v2.materiaAgrupacio;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public interface MateriaAgrupacioQueryServiceStrategy {

    MateriaDTO obtenirMateria(Long idMateria);

    AgrupacioMateriaDTO obtenirAgrupacioMateria(Long idAgrupacion);

}
