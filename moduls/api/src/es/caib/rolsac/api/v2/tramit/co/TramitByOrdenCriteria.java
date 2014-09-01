package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TramitByOrdenCriteria extends ByLongCriteria {

    public TramitByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
