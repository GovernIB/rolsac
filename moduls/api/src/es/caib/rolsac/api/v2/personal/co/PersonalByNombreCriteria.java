package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByNombreCriteria extends ByStringCriteria {

    public PersonalByNombreCriteria(String entityAlias) {
        super(entityAlias + ".nombre");
    }

}
