package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByBusinessKeyCriteria extends ByStringCriteria {

    public UnitatAdministrativaByBusinessKeyCriteria(String entityAlias) {
        super(entityAlias + ".businessKey");
    }

}
