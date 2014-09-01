package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByFuncionesCriteria extends ByStringCriteria {

    public PersonalByFuncionesCriteria(String entityAlias) {
        super(entityAlias + ".funciones");
    }

}
