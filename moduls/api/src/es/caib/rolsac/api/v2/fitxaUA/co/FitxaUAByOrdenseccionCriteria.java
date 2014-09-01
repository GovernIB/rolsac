package es.caib.rolsac.api.v2.fitxaUA.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class FitxaUAByOrdenseccionCriteria extends ByLongCriteria {
    
    public FitxaUAByOrdenseccionCriteria(String entityAlias) {
        super(entityAlias + ".ordenseccion");
    }

}
