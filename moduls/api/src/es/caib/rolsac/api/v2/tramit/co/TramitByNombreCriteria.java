package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByNombreCriteria extends ByStringCriteria {

    public TramitByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
