package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByResponsableCriteria extends ByStringCriteria {

    public ProcedimentByResponsableCriteria(String entityAlias) {
        super(entityAlias + ".responsable");
    }

}
