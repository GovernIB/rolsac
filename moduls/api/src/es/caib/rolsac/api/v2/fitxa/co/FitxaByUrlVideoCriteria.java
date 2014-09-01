package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByUrlVideoCriteria extends ByStringCriteria {
    
    public FitxaByUrlVideoCriteria(String entityAlias) {
        super(entityAlias + ".urlVideo");
    }

}
