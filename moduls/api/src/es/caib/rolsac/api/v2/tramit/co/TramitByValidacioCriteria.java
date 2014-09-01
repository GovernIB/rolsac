package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TramitByValidacioCriteria extends ByLongCriteria {

    public TramitByValidacioCriteria(String entityAlias) {
        super(entityAlias + ".validacio");
    }

}
