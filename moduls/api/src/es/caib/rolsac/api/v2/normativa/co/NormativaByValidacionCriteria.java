package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByValidacionCriteria extends ByLongCriteria {

    public NormativaByValidacionCriteria(String entityAlias) {
        super(entityAlias + ".validacion");
    }

}
