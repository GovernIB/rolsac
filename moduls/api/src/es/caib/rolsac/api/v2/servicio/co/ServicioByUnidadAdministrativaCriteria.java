package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByUnidadAdministrativaCriteria extends ByLongCriteria {

    public ServicioByUnidadAdministrativaCriteria(String entityAlias) {
        super(entityAlias + ".unidadAdministrativa.id");
    }

}
