package es.caib.rolsac.api.v2.personal.ejb;

import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaCriteria;
import es.caib.rolsac.api.v2.unitatAdministrativa.UnitatAdministrativaDTO;

public class PersonalQueryServiceEJB {
    
    public UnitatAdministrativaDTO obtenirUnitatAdministrativa(long idUA) {
        UnitatAdministrativaCriteria criteria = new UnitatAdministrativaCriteria();
        criteria.setId(String.valueOf(idUA));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirUnitatAdministrativa(criteria);
    }

}
