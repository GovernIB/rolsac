package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByVentanillaUnicaCriteria extends ByStringCriteria {

    public ProcedimentByVentanillaUnicaCriteria(String entityAlias) {
        super(entityAlias + ".ventanillaUnica");
    }

}
