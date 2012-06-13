package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class FitxaByValidacionCriteria extends ByLongCriteria {
    
    public FitxaByValidacionCriteria(String entityAlias) {
        super(entityAlias + ".validacion");
    }

}
