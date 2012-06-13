package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByUsernameCriteria extends ByStringCriteria {

    public PersonalByUsernameCriteria(String entityAlias) {
        super(entityAlias + ".username");
    }

}
