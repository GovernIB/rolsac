package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByIndicadorCriteria extends ByStringCriteria {

    public ServicioByIndicadorCriteria(String entityAlias) {
        super(entityAlias + ".indicador");
    }

}
