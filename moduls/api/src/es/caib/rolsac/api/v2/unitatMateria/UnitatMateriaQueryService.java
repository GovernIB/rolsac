package es.caib.rolsac.api.v2.unitatMateria;

import es.caib.rolsac.api.v2.materia.MateriaQueryServiceAdapter;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public interface UnitatMateriaQueryService {

    UnitatAdministrativaQueryServiceAdapter obtenirUnitatAdministrativa();

    MateriaQueryServiceAdapter obtenirMateria();

}
