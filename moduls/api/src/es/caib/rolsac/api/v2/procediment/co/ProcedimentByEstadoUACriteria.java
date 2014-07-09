package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByEstadoUACriteria extends ByLongCriteria {

    public ProcedimentByEstadoUACriteria(String entityAlias) {
        super(entityAlias + ".unidadAdministrativa.validacion");
    }

}
