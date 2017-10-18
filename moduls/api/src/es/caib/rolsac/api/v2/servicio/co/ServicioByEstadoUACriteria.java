package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByEstadoUACriteria extends ByLongCriteria {

    public ServicioByEstadoUACriteria(String entityAlias) {
        super(entityAlias + ".unidadAdministrativa.validacion");
    }

}
