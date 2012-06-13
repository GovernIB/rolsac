package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByNumeroCriteria extends ByLongCriteria {

    public NormativaByNumeroCriteria(String entityAlias) {
        super(entityAlias + ".numero");
    }

}
