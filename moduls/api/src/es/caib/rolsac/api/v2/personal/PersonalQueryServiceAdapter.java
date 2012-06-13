package es.caib.rolsac.api.v2.personal;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.personal.ejb.PersonalQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class PersonalQueryServiceAdapter extends PersonalDTO implements PersonalQueryService {

    PersonalQueryServiceStrategy personalQueryServiceStrategy;

    public PersonalQueryServiceAdapter() {
        // FIXME: don't harcode the PersonalQueryServiceEJBStrategy.
        personalQueryServiceStrategy = new PersonalQueryServiceEJBStrategy();
    }

    public PersonalQueryServiceAdapter(PersonalDTO dto) {
        this();
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    public UnitatAdministrativaQueryService obtenirUnitatAdministrativa(
            UnitatAdministrativaCriteria unitatAdministrativaCriteria) {

        UnitatAdministrativaDTO dto = personalQueryServiceStrategy.obtenirUnitatAdministrativa(id,
                unitatAdministrativaCriteria);
        return new UnitatAdministrativaQueryServiceAdapter(dto);
    }

}
