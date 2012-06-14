package es.caib.rolsac.api.v2.personal;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.personal.ejb.PersonalQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryService;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaQueryServiceAdapter;

public class PersonalQueryServiceAdapter extends PersonalDTO implements PersonalQueryService {

    private PersonalQueryServiceStrategy personalQueryServiceStrategy;

    public void setPersonalQueryServiceStrategy(PersonalQueryServiceStrategy personalQueryServiceStrategy) {
        this.personalQueryServiceStrategy = personalQueryServiceStrategy;
    }

    public PersonalQueryServiceAdapter(PersonalDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }

    private STRATEGY getStrategy() {
        return personalQueryServiceStrategy instanceof PersonalQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public UnitatAdministrativaQueryService obtenirUnitatAdministrativa() {
        if (this.getUnidadAdministrativa() == null) {return null;}
        UnitatAdministrativaDTO dto = personalQueryServiceStrategy.obtenirUnitatAdministrativa(this.getUnidadAdministrativa());
        return (UnitatAdministrativaQueryServiceAdapter) BeanUtils.getAdapter("unitatAdministrativa", getStrategy(), dto);
    }

}
