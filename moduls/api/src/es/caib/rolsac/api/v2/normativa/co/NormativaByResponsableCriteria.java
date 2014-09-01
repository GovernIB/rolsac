package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByResponsableCriteria extends ByStringCriteria {

    public NormativaByResponsableCriteria(String i18nAlias) {
        super(i18nAlias + ".responsable");
    }

}
