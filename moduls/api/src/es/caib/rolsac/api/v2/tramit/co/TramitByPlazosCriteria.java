package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByPlazosCriteria extends ByStringCriteria {

    public TramitByPlazosCriteria(String i18nAlias) {
        super(i18nAlias + ".plazos");
    }

}
