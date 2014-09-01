package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentBySignaturaCriteria extends ByStringCriteria {

    public ProcedimentBySignaturaCriteria(String entityAlias) {
        super(entityAlias + ".signatura");
    }

}
