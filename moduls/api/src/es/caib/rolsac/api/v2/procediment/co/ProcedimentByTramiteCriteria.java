package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByTramiteCriteria extends ByStringCriteria {

    public ProcedimentByTramiteCriteria(String entityAlias) {
        super(entityAlias + ".tramite");
    }

}
