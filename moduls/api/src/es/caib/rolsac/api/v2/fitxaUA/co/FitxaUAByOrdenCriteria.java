package es.caib.rolsac.api.v2.fitxaUA.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class FitxaUAByOrdenCriteria extends ByLongCriteria {
    
    public FitxaUAByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
