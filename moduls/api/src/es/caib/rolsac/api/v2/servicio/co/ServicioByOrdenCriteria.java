package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByOrdenCriteria extends ByLongCriteria {

    public ServicioByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
