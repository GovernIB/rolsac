package es.caib.rolsac.api.v2.materiaAgrupacio;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaDTO;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaDTO;

public interface MateriaAgrupacioQueryServiceStrategy {

    MateriaDTO obtenirMateria(long id, MateriaCriteria materiaCriteria);

    AgrupacioMateriaDTO obtenirAgrupacioMateria(long id, AgrupacioMateriaCriteria agrupacioMateriaCriteria);

}
