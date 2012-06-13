package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByCodigoEstandarCriteria extends ByStringCriteria {

    public UnitatAdministrativaByCodigoEstandarCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandar");
    }

}
