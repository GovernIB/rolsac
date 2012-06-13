package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByNombreCriteria extends ByStringCriteria {

    public ProcedimentByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
