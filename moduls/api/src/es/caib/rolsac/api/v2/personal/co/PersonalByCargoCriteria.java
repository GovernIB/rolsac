package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByCargoCriteria extends ByStringCriteria {

    public PersonalByCargoCriteria(String entityAlias) {
        super(entityAlias + ".cargo");
    }

}
