package es.caib.rolsac.api.v2.plataforma.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlataformaByCodigoEstandardCriteria extends ByStringCriteria {

    public PlataformaByCodigoEstandardCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandard");
    }

}
