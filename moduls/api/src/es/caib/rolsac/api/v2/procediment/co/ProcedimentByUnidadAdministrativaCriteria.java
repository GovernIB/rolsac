package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByUnidadAdministrativaCriteria extends ByLongCriteria {

    public ProcedimentByUnidadAdministrativaCriteria(String entityAlias) {
        super(entityAlias + ".unidadAdministrativa.id");
    }

}
