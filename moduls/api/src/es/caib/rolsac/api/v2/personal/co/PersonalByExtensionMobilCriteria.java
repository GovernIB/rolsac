package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByExtensionMobilCriteria extends ByStringCriteria {

    public PersonalByExtensionMobilCriteria(String entityAlias) {
        super(entityAlias + ".extensionMobil");
    }

}
