package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByVersionCriteria extends ByLongCriteria {

    public ProcedimentByVersionCriteria(String entityAlias) {
        super(entityAlias + ".version");
    }

}
