package es.caib.rolsac.api.v2.fitxaUA.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class FitxaUAByUnidadAdministrativaCriteria extends ByLongCriteria {
    
    public FitxaUAByUnidadAdministrativaCriteria(String entityAlias) {
        super(entityAlias + ".unidadAdministrativa");
    }

}
