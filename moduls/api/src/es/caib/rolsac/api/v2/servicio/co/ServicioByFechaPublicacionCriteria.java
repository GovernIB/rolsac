package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class ServicioByFechaPublicacionCriteria extends ByDateCriteria {

    public ServicioByFechaPublicacionCriteria(String entityAlias) {
        super(entityAlias + ".fechaPublicacion");
    }

}
