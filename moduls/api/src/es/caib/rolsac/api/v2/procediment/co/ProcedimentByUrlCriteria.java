package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByUrlCriteria extends ByStringCriteria {

    public ProcedimentByUrlCriteria(String entityAlias) {
        super(entityAlias + ".url");
    }

}
