package es.caib.rolsac.api.v2.taxa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TaxaByOrdenCriteria extends ByLongCriteria {

    public TaxaByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
