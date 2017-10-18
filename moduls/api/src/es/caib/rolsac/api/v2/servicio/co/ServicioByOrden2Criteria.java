package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByOrden2Criteria extends ByLongCriteria {

    public ServicioByOrden2Criteria(String entityAlias) {
        super(entityAlias + ".orden2");
    }

}
