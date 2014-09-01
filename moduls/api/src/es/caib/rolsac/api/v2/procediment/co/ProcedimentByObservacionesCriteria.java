package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByObservacionesCriteria extends ByStringCriteria {

    public ProcedimentByObservacionesCriteria(String i18nAlias) {
        super(i18nAlias + ".observaciones");
    }

}
