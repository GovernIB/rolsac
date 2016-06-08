package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByDirElectronicaCriteria extends ByStringCriteria {

    public ProcedimentByDirElectronicaCriteria(String entityAlias) {
        super(entityAlias + ".dirElectronica");
    }

}
