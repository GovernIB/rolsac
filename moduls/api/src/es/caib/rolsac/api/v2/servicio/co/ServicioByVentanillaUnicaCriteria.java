package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByVentanillaUnicaCriteria extends ByStringCriteria {

    public ServicioByVentanillaUnicaCriteria(String entityAlias) {
        super(entityAlias + ".ventanillaUnica");
    }

}
