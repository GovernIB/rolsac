package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioBySignaturaCriteria extends ByStringCriteria {

    public ServicioBySignaturaCriteria(String entityAlias) {
        super(entityAlias + ".signatura");
    }

}
