package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByTipoCriteria extends ByLongCriteria {

    public NormativaByTipoCriteria(String entityAlias) {
        super(entityAlias + ".id");
    }

}
