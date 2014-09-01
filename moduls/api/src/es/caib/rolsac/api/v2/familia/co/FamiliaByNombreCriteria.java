package es.caib.rolsac.api.v2.familia.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FamiliaByNombreCriteria extends ByStringCriteria {

    public FamiliaByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
