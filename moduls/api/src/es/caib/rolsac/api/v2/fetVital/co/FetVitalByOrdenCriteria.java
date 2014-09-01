package es.caib.rolsac.api.v2.fetVital.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class FetVitalByOrdenCriteria extends ByLongCriteria {

    public FetVitalByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
