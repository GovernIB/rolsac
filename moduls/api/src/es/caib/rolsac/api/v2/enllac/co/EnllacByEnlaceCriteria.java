package es.caib.rolsac.api.v2.enllac.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EnllacByEnlaceCriteria extends ByStringCriteria {

    public EnllacByEnlaceCriteria(String i18nAlias) {
        super(i18nAlias + ".enlace");
    }

}
