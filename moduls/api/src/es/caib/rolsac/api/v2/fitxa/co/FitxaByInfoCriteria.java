package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByInfoCriteria extends ByStringCriteria {
    
    public FitxaByInfoCriteria(String entityAlias) {
        super(entityAlias + ".info");
    }

}
