package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByTaxaCriteria extends ByStringCriteria {

    public ProcedimentByTaxaCriteria(String entityAlias) {
        super(entityAlias + ".taxa");
    }

}
