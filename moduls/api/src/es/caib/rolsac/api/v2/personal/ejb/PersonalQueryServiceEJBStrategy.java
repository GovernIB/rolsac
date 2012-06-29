package es.caib.rolsac.api.v2.personal.ejb;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.personal.PersonalQueryServiceStrategy;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceEJBStrategy implements PersonalQueryServiceStrategy {

    private PersonalQueryServiceDelegate personalQueryServiceDelegate;

    public void setPersonalQueryServiceDelegate(PersonalQueryServiceDelegate personalQueryServiceDelegate) {
        this.personalQueryServiceDelegate = personalQueryServiceDelegate;
    }
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA) throws StrategyException {
        try {
            return personalQueryServiceDelegate.obtenirUnitatAdministrativa(idUA);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
