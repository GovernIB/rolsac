package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class ProcedimentByFechaPublicacionCriteria extends ByDateCriteria {

    public ProcedimentByFechaPublicacionCriteria(String entityAlias) {
        super(entityAlias + ".fechaPublicacion");
    }

}
