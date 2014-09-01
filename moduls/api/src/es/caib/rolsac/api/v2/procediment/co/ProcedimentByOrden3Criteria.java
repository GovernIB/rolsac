package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByOrden3Criteria extends ByLongCriteria {

    public ProcedimentByOrden3Criteria(String entityAlias) {
        super(entityAlias + ".orden3");
    }

}
