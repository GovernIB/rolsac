package es.caib.rolsac.api.v2.seccio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class SeccioByNombreCriteria extends ByStringCriteria {

    public SeccioByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
