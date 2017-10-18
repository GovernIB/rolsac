package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ServicioByFamiliaCriteria extends ByLongCriteria {

    public ServicioByFamiliaCriteria(String entityAlias) {
        super(entityAlias + ".familia.id");
    }

}
