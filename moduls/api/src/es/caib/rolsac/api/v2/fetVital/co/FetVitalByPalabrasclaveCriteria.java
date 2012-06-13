package es.caib.rolsac.api.v2.fetVital.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FetVitalByPalabrasclaveCriteria extends ByStringCriteria {

    public FetVitalByPalabrasclaveCriteria(String i18nAlias) {
        super(i18nAlias + ".palabrasclave");
    }

}
