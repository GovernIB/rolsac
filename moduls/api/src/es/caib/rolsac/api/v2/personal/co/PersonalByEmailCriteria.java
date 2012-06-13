package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByEmailCriteria extends ByStringCriteria {

    public PersonalByEmailCriteria(String entityAlias) {
        super(entityAlias + ".email");
    }

}
