package es.caib.rolsac.api.v2.idioma.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class IdiomaByNombreCriteria extends ByStringCriteria {

    public IdiomaByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
