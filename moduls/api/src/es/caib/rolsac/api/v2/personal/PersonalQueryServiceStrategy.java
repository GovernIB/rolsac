package es.caib.rolsac.api.v2.personal;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public interface PersonalQueryServiceStrategy {

    UnitatAdministrativaDTO obtenirUnitatAdministrativa(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria);

}
