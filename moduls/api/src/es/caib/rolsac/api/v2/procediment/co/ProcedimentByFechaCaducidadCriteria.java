package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class ProcedimentByFechaCaducidadCriteria extends ByDateCriteria {

    public ProcedimentByFechaCaducidadCriteria(String entityAlias) {
        super(entityAlias + ".fechaCaducidad");
    }

}
