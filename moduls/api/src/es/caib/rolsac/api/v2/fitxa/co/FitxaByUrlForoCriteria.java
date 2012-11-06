package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByUrlForoCriteria extends ByStringCriteria {
    
    public FitxaByUrlForoCriteria(String entityAlias) {
        super(entityAlias + ".urlForo");
    }

}