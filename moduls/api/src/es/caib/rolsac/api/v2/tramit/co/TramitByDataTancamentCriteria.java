package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class TramitByDataTancamentCriteria extends ByDateCriteria {

    public TramitByDataTancamentCriteria(String entityAlias) {
        super(entityAlias + ".dataTancament");
    }

}
