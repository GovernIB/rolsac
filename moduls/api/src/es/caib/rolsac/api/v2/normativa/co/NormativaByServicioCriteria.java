package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByServicioCriteria extends ByLongCriteria {

    public NormativaByServicioCriteria(String entityAlias) {
        super(entityAlias + ".id");
    }

}
