package es.caib.rolsac.api.v2.personal.ejb;

import es.caib.rolsac.api.v2.personal.PersonalQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceEJBStrategy implements PersonalQueryServiceStrategy {

    private PersonalQueryServiceDelegate personalQueryServiceDelegate;

    public void setPersonalQueryServiceDelegate(PersonalQueryServiceDelegate personalQueryServiceDelegate) {
        this.personalQueryServiceDelegate = personalQueryServiceDelegate;
    }
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA) {
        return personalQueryServiceDelegate.obtenirUnitatAdministrativa(idUA);
    }

}
