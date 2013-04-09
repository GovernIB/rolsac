package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByLugarCriteria extends ByStringCriteria {

    public TramitByLugarCriteria(String i18nAlias) {
        super(i18nAlias + ".lugar");
    }

}