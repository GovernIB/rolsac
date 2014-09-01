package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByInfoCriteria extends ByStringCriteria {

    public ProcedimentByInfoCriteria(String entityAlias) {
        super(entityAlias + ".info");
    }

}
