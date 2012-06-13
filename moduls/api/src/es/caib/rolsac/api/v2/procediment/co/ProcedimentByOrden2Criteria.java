package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByOrden2Criteria extends ByLongCriteria {

    public ProcedimentByOrden2Criteria(String entityAlias) {
        super(entityAlias + ".orden2");
    }

}
