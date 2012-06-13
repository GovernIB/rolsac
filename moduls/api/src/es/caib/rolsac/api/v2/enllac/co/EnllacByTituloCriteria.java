package es.caib.rolsac.api.v2.enllac.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EnllacByTituloCriteria extends ByStringCriteria {

    public EnllacByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
