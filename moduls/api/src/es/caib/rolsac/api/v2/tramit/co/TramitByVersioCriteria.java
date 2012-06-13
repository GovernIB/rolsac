package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TramitByVersioCriteria extends ByLongCriteria {

    public TramitByVersioCriteria(String entityAlias) {
        super(entityAlias + ".versio");
    }

}
