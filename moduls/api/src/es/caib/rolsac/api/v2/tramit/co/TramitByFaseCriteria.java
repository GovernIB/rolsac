package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TramitByFaseCriteria extends ByLongCriteria {

    public TramitByFaseCriteria(String entityAlias) {
        super(entityAlias + ".fase");
    }

}
