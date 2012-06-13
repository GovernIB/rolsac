package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByLugarCriteria extends ByStringCriteria {

    public ProcedimentByLugarCriteria(String i18nAlias) {
        super(i18nAlias + ".lugar");
    }

}
