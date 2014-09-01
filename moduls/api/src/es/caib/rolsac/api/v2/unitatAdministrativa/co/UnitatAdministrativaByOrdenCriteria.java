package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class UnitatAdministrativaByOrdenCriteria extends ByLongCriteria {

    public UnitatAdministrativaByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
