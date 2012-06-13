package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class UnitatAdministrativaByValidacionCriteria extends ByLongCriteria {

    public UnitatAdministrativaByValidacionCriteria(String entityAlias) {
        super(entityAlias + ".validacion");
    }

}
