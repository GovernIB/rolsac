package es.caib.rolsac.api.v2.materiaAgrupacio;

import es.caib.rolsac.api.v2.agrupacioMateria.AgrupacioMateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;

public interface MateriaAgrupacioQueryService {

    public MateriaQueryServiceAdapter obtenirMateria() throws QueryServiceException;

    public AgrupacioMateriaQueryServiceAdapter obtenirAgrupacio() throws QueryServiceException;

}
