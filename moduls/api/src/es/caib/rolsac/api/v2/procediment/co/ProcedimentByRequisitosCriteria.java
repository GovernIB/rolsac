package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByRequisitosCriteria extends ByStringCriteria {

    public ProcedimentByRequisitosCriteria(String i18nAlias) {
        super(i18nAlias + ".requisitos");
    }

}
