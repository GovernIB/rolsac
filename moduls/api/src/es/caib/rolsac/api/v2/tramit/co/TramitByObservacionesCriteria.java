package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByObservacionesCriteria extends ByStringCriteria {

    public TramitByObservacionesCriteria(String i18nAlias) {
        super(i18nAlias + ".observaciones");
    }

}
