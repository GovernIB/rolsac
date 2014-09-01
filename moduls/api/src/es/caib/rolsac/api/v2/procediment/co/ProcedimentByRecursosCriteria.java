package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByRecursosCriteria extends ByStringCriteria {

    public ProcedimentByRecursosCriteria(String i18nAlias) {
        super(i18nAlias + ".recursos");
    }

}
