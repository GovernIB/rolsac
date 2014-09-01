package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaBySeccionCriteria extends ByStringCriteria {

    public NormativaBySeccionCriteria(String i18nAlias) {
        super(i18nAlias + ".seccion");
    }

}
