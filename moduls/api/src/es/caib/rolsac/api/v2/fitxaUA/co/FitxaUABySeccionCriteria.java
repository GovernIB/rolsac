package es.caib.rolsac.api.v2.fitxaUA.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class FitxaUABySeccionCriteria extends ByLongCriteria {
    
    public FitxaUABySeccionCriteria(String entityAlias) {
        super(entityAlias + ".seccion");
    }

}
