package es.caib.rolsac.api.v2.taxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TaxaByCodificacioCriteria extends ByStringCriteria {

    public TaxaByCodificacioCriteria(String i18nAlias) {
        super(i18nAlias + ".codificacio");
    }

}
