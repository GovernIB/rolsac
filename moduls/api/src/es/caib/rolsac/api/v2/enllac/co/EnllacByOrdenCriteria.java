package es.caib.rolsac.api.v2.enllac.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class EnllacByOrdenCriteria extends ByLongCriteria {

    public EnllacByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
