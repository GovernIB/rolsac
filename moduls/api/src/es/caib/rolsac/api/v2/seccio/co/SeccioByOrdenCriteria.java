package es.caib.rolsac.api.v2.seccio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class SeccioByOrdenCriteria extends ByLongCriteria {

    public SeccioByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
