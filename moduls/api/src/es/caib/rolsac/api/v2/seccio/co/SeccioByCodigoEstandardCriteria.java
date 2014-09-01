package es.caib.rolsac.api.v2.seccio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class SeccioByCodigoEstandardCriteria extends ByStringCriteria {

    public SeccioByCodigoEstandardCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandard");
    }

}
