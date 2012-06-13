package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class NormativaByFechaCriteria extends ByDateCriteria {

    public NormativaByFechaCriteria(String entityAlias) {
        super(entityAlias + ".fecha");
    }

}
