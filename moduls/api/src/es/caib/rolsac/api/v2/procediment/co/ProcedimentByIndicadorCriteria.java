package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByIndicadorCriteria extends ByStringCriteria {

    public ProcedimentByIndicadorCriteria(String entityAlias) {
        super(entityAlias + ".indicador");
    }

}
