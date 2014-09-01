package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByButlletiCriteria extends ByLongCriteria {

    public NormativaByButlletiCriteria(String entityAlias) {
        super(entityAlias + ".id");
    }

}
