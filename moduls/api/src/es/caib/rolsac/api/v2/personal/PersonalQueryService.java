package es.caib.rolsac.api.v2.personal;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;

public interface PersonalQueryService {

    UnitatAdministrativaQueryService obtenirUnitatAdministrativa(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

}
