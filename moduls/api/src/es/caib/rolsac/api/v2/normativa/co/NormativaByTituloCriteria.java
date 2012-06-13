package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByTituloCriteria extends ByStringCriteria {

    public NormativaByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
