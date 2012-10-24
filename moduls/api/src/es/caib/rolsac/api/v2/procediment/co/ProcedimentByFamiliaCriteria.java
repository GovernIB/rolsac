package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class ProcedimentByFamiliaCriteria extends ByLongCriteria {

    public ProcedimentByFamiliaCriteria(String entityAlias) {
        super(entityAlias + ".familia.id");
    }

}
