package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByResponsableCriteria extends ByStringCriteria {
    
    public FitxaByResponsableCriteria(String entityAlias) {
        super(entityAlias + ".responsable");
    }

}
