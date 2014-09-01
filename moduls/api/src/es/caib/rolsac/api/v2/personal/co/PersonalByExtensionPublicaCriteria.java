package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByExtensionPublicaCriteria extends ByStringCriteria {

    public PersonalByExtensionPublicaCriteria(String entityAlias) {
        super(entityAlias + ".extensionPublica");
    }

}
