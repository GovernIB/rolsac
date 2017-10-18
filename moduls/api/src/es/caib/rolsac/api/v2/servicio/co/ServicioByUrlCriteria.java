package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByUrlCriteria extends ByStringCriteria {

    public ServicioByUrlCriteria(String entityAlias) {
        super(entityAlias + ".url");
    }

}
