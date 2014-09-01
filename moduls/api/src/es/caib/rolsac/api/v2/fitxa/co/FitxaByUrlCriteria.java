package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByUrlCriteria extends ByStringCriteria {
    
    public FitxaByUrlCriteria(String i18nAlias) {
        super(i18nAlias + ".url");
    }

}
