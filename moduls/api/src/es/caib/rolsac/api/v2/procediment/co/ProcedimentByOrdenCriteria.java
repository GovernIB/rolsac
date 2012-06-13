package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByOrdenCriteria extends ByLongCriteria {

    public ProcedimentByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
