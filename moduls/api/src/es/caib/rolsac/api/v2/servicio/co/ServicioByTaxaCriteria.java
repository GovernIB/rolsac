package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByTaxaCriteria extends ByStringCriteria {

    public ServicioByTaxaCriteria(String entityAlias) {
        super(entityAlias + ".taxa");
    }

}
