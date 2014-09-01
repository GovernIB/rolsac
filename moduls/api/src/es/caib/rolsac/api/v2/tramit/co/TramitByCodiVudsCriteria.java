package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByCodiVudsCriteria extends ByStringCriteria {

    public TramitByCodiVudsCriteria(String entityAlias) {
        super(entityAlias + ".codiVuds");
    }

}
