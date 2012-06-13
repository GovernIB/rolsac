package es.caib.rolsac.api.v2.taxa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class TaxaByTramitCriteria extends ByLongCriteria {

    public TaxaByTramitCriteria(String entityAlias) {
        super(entityAlias + ".tramit");
    }

}
