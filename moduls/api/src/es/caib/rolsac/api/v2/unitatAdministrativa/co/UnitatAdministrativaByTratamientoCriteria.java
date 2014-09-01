package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class UnitatAdministrativaByTratamientoCriteria extends ByLongCriteria {

    public UnitatAdministrativaByTratamientoCriteria(String entityAlias) {
        super(entityAlias + ".tratamiento");
    }

}
