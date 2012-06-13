package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByFaxCriteria extends ByStringCriteria {

    public UnitatAdministrativaByFaxCriteria(String entityAlias) {
        super(entityAlias + ".fax");
    }

}
