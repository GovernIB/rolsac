package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class TramitByDataIniciCriteria extends ByDateCriteria {

    public TramitByDataIniciCriteria(String entityAlias) {
        super(entityAlias + ".dataInici");
    }

}
