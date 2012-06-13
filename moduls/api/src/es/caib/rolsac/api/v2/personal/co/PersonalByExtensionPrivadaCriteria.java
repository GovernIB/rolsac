package es.caib.rolsac.api.v2.personal.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PersonalByExtensionPrivadaCriteria extends ByStringCriteria {

    public PersonalByExtensionPrivadaCriteria(String entityAlias) {
        super(entityAlias + ".extensionPrivada");
    }

}
