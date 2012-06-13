package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByDominioCriteria extends ByStringCriteria {

    public UnitatAdministrativaByDominioCriteria(String entityAlias) {
        super(entityAlias + ".dominio");
    }

}
