package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByValidacionCriteria extends ByLongCriteria {

    public ProcedimentByValidacionCriteria(String entityAlias) {
        super(entityAlias + ".validacion");
    }

}
