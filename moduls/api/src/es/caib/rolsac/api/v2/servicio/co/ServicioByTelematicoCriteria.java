package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByTelematicoCriteria extends ByLongCriteria {

    public ServicioByTelematicoCriteria(String entityAlias) {
        super(entityAlias + ".telematico");
    }

}
