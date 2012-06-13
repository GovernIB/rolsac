package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FitxaByDescAbrCriteria extends ByStringCriteria {
    
    public FitxaByDescAbrCriteria(String i18nAlias) {
        super(i18nAlias + ".descAbr");
    }

}
