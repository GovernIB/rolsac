package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByTelefonoCriteria extends ByStringCriteria {

    public UnitatAdministrativaByTelefonoCriteria(String entityAlias) {
        super(entityAlias + ".telefono");
    }

}
