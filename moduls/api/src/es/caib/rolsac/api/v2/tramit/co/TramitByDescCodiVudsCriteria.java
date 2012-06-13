package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByDescCodiVudsCriteria extends ByStringCriteria {

    public TramitByDescCodiVudsCriteria(String entityAlias) {
        super(entityAlias + ".descCodiVuds");
    }

}
