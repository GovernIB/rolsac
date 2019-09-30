package es.caib.rolsac.api.v2.plataforma.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlataformaByUrlAccesoCriteria extends ByStringCriteria {

    public PlataformaByUrlAccesoCriteria(String entityAlias) {
        super(entityAlias + ".pathIconografia");
    }

}
