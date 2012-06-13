package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByPresentacionCriteria extends ByStringCriteria {

    public UnitatAdministrativaByPresentacionCriteria(String i18nAlias) {
        super(i18nAlias + ".presentacion");
    }

}
