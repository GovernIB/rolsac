package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByNumeroLargoPublicoCriteria extends ByStringCriteria {

    public PersonalByNumeroLargoPublicoCriteria(String entityAlias) {
        super(entityAlias + ".numeroLargoPublico");
    }

}
