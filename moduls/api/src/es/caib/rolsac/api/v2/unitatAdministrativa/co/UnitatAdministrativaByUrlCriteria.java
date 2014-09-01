package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByUrlCriteria extends ByStringCriteria {

    public UnitatAdministrativaByUrlCriteria(String entityAlias) {
        super(entityAlias + ".url");
    }

}
