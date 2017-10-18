package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByOrden3Criteria extends ByLongCriteria {

    public ServicioByOrden3Criteria(String entityAlias) {
        super(entityAlias + ".orden3");
    }

}
