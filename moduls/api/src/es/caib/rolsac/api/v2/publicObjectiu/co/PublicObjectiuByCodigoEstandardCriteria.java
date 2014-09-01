package es.caib.rolsac.api.v2.publicObjectiu.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PublicObjectiuByCodigoEstandardCriteria extends ByStringCriteria {

    public PublicObjectiuByCodigoEstandardCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandard");
    }

}
