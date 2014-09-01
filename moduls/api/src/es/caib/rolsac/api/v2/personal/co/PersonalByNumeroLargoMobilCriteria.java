package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByNumeroLargoMobilCriteria extends ByStringCriteria {

    public PersonalByNumeroLargoMobilCriteria(String entityAlias) {
        super(entityAlias + ".numeroLargoMobil");
    }

}
