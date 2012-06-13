package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class NormativaByLeyCriteria extends ByStringCriteria {

    public NormativaByLeyCriteria(String entityAlias) {
        super(entityAlias + ".ley");
    }

}
