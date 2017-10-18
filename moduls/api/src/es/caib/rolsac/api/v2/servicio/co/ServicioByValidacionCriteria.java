package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByValidacionCriteria extends ByLongCriteria {

    public ServicioByValidacionCriteria(String entityAlias) {
        super(entityAlias + ".validacion");
    }

}
