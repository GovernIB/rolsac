package es.caib.rolsac.api.v2.taxa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TaxaByFormaPagamentCriteria extends ByStringCriteria {

    public TaxaByFormaPagamentCriteria(String i18nAlias) {
        super(i18nAlias + ".formaPagament");
    }

}
