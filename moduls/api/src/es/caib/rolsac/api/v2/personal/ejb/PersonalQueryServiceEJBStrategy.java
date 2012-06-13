package es.caib.rolsac.api.v2.personal.ejb;

import es.caib.rolsac.api.v2.personal.PersonalQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceEJBStrategy implements PersonalQueryServiceStrategy {

    PersonalQueryServiceDelegate delegate;
    PersonalQueryServiceLocator locator;

    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long id,
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
