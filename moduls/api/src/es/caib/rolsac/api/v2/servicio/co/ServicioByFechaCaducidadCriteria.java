package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class ServicioByFechaCaducidadCriteria extends ByDateCriteria {

    public ServicioByFechaCaducidadCriteria(String entityAlias) {
        super(entityAlias + ".fechaCaducidad");
    }

}
