package es.caib.rolsac.api.v2.fetVital.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FetVitalByNombreCriteria extends ByStringCriteria {

    public FetVitalByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
