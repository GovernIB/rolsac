package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByTituloCriteria extends ByStringCriteria {
    
    public FitxaByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
