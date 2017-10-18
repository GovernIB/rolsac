package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class ServicioByFechaActualizacionCriteria extends ByDateCriteria {

    public ServicioByFechaActualizacionCriteria(String entityAlias) {
        super(entityAlias + ".fechaActualizacion");
    }

}
