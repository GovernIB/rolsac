package es.caib.rolsac.api.v2.enllactelematico.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class EnllacTelematicoByOrdenCriteria extends ByLongCriteria {

    public EnllacTelematicoByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
