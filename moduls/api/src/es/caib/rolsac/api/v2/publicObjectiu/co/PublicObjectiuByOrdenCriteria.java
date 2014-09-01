package es.caib.rolsac.api.v2.publicObjectiu.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class PublicObjectiuByOrdenCriteria extends ByLongCriteria {

    public PublicObjectiuByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
