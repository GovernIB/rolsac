package es.caib.rolsac.api.v2.fitxaUA.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class FitxaUAByFitxaCriteria extends ByLongCriteria {
    
    public FitxaUAByFitxaCriteria(String entityAlias) {
        super(entityAlias + ".ficha");
    }

}
