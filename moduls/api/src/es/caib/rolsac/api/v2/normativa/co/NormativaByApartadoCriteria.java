package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByApartadoCriteria extends ByStringCriteria {

    public NormativaByApartadoCriteria(String i18nAlias) {
        super(i18nAlias + ".apartado");
    }

}
