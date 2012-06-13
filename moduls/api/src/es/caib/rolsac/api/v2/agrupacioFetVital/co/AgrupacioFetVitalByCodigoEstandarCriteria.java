package es.caib.rolsac.api.v2.agrupacioFetVital.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class AgrupacioFetVitalByCodigoEstandarCriteria extends ByStringCriteria {

    public AgrupacioFetVitalByCodigoEstandarCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandar");
    }

}
