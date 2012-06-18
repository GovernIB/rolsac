package es.caib.rolsac.api.v2.materiaAgrupacio;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;

public interface MateriaAgrupacioQueryService {

    MateriaQueryServiceAdapter obtenirMateria();

    AgrupacioMateriaQueryServiceAdapter obtenirAgrupacio();

}
