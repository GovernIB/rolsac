package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByEmailCriteria extends ByStringCriteria {

    public UnitatAdministrativaByEmailCriteria(String entityAlias) {
        super(entityAlias + ".email");
    }

}
