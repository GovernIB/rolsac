package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByResponsableCriteria extends ByStringCriteria {

    public UnitatAdministrativaByResponsableCriteria(String entityAlias) {
        super(entityAlias + ".responsable");
    }

}
