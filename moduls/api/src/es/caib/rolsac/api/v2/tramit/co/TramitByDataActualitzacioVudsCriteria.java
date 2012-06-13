package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByDataActualitzacioVudsCriteria extends ByStringCriteria {

    public TramitByDataActualitzacioVudsCriteria(String entityAlias) {
        super(entityAlias + ".dataActualitzacioVuds");
    }

}
