package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByDescCodiVudsCriteria extends ByStringCriteria {

    public NormativaByDescCodiVudsCriteria(String entityAlias) {
        super(entityAlias + ".descCodiVuds");
    }

}
