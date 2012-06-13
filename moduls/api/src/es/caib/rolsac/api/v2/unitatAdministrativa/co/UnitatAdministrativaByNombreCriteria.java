package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByNombreCriteria extends ByStringCriteria {

    public UnitatAdministrativaByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
