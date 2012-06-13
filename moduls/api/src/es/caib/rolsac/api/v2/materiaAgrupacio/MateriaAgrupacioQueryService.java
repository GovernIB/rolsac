package es.caib.rolsac.api.v2.materiaAgrupacio;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaCriteria;
import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryService;
import es.caib.rolsac.api.v2.materia.MateriaCriteria;
import es.caib.rolsac.api.v2.materia.MateriaQueryService;

public interface MateriaAgrupacioQueryService {

    MateriaQueryService obtenirMateria(MateriaCriteria materiaCriteria);

    AgrupacioMateriaQueryService obtenirAgrupacio(AgrupacioMateriaCriteria agrupacioMateriaCriteria);

}
