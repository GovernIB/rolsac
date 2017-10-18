package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByVersionCriteria extends ByLongCriteria {

    public ServicioByVersionCriteria(String entityAlias) {
        super(entityAlias + ".version");
    }

}
