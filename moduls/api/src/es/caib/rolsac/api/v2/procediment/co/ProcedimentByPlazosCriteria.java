package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByPlazosCriteria extends ByStringCriteria {

    public ProcedimentByPlazosCriteria(String i18nAlias) {
        super(i18nAlias + ".plazos");
    }

}
