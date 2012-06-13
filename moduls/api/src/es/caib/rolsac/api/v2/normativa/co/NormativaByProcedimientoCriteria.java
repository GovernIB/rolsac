package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByProcedimientoCriteria extends ByLongCriteria {

    public NormativaByProcedimientoCriteria(String entityAlias) {
        super(entityAlias + ".id");
    }

}
