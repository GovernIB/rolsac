package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class ProcedimentByFechaActualizacionCriteria extends ByDateCriteria {

    public ProcedimentByFechaActualizacionCriteria(String entityAlias) {
        super(entityAlias + ".fechaActualizacion");
    }

}
