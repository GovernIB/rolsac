package es.caib.rolsac.api.v2.personal.ejb;

import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceDelegate {
    
    private PersonalQueryServiceEJBLocator personalQueryServiceLocator;

    public void setPersonalQueryServiceLocator(PersonalQueryServiceEJBLocator personalQueryServiceLocator) {
        this.personalQueryServiceLocator = personalQueryServiceLocator;
    }
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA) {
        PersonalQueryServiceEJB ejb =  personalQueryServiceLocator.getPersonalQueryServiceEJB();
        return ejb.obtenirUnitatAdministrativa(idUA);
    }

}
