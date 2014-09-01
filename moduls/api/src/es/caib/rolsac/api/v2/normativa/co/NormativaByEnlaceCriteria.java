package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByEnlaceCriteria extends ByStringCriteria {

    public NormativaByEnlaceCriteria(String i18nAlias) {
        super(i18nAlias + ".enlace");
    }

}
