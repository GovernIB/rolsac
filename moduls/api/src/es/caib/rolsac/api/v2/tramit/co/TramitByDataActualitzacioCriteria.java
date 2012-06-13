package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class TramitByDataActualitzacioCriteria extends ByDateCriteria {

    public TramitByDataActualitzacioCriteria(String entityAlias) {
        super(entityAlias + ".dataActualitzacio");
    }

}
