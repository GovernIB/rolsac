package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByResponsableCriteria extends ByStringCriteria {

    public ServicioByResponsableCriteria(String entityAlias) {
        super(entityAlias + ".responsable");
    }

}
