package es.caib.rolsac.api.v2.unitatMateria;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface UnitatMateriaQueryService {

    public UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa() throws QueryServiceException;

    public MateriaQueryServiceAdapter obtenirMateria() throws QueryServiceException;

}
