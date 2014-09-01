package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByForo_temaCriteria extends ByStringCriteria {
    
    public FitxaByForo_temaCriteria(String entityAlias) {
        super(entityAlias + ".foro_tema");
    }

}
