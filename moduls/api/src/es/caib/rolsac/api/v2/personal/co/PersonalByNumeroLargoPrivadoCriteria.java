package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByNumeroLargoPrivadoCriteria extends ByStringCriteria {

    public PersonalByNumeroLargoPrivadoCriteria(String entityAlias) {
        super(entityAlias + ".numeroLargoPrivado");
    }

}
