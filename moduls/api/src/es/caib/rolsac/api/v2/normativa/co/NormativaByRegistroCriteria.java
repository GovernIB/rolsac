package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByRegistroCriteria extends ByLongCriteria {

    public NormativaByRegistroCriteria(String entityAlias) {
        super(entityAlias + ".registro");
    }

}
