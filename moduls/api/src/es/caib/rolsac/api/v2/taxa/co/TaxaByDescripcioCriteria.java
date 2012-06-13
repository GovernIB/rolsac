package es.caib.rolsac.api.v2.taxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TaxaByDescripcioCriteria extends ByStringCriteria {

    public TaxaByDescripcioCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcio");
    }

}
