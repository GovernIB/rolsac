package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByAbreviaturaCriteria extends ByStringCriteria {

    public UnitatAdministrativaByAbreviaturaCriteria(String i18nAlias) {
        super(i18nAlias + ".abreviatura");
    }

}
