package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByUrlExternaCriteria extends ByStringCriteria {

    public TramitByUrlExternaCriteria(String entityAlias) {
        super(entityAlias + ".urlExterna");
    }

}
