package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByCodiVudsCriteria extends ByStringCriteria {

    public NormativaByCodiVudsCriteria(String entityAlias) {
        super(entityAlias + ".codiVuds");
    }

}
