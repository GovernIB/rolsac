package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class NormativaByUnitatAdministrativaCriteria extends ByLongCriteria {

    public NormativaByUnitatAdministrativaCriteria(String entityAlias) {
        super(entityAlias + ".id");
    }

}
