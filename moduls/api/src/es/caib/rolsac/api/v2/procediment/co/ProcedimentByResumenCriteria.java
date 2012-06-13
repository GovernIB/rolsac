package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByResumenCriteria extends ByStringCriteria {

    public ProcedimentByResumenCriteria(String i18nAlias) {
        super(i18nAlias + ".resumen");
    }

}
