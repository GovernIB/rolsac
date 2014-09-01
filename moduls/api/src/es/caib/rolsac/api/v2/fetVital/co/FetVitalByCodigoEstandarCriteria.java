package es.caib.rolsac.api.v2.fetVital.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FetVitalByCodigoEstandarCriteria extends ByStringCriteria {

    public FetVitalByCodigoEstandarCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandar");
    }

}
