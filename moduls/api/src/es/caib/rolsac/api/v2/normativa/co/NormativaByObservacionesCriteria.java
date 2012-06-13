package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByObservacionesCriteria extends ByStringCriteria {

    public NormativaByObservacionesCriteria(String i18nAlias) {
        super(i18nAlias + ".observaciones");
    }

}
