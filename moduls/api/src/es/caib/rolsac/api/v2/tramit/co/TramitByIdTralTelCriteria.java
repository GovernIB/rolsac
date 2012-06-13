package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByIdTralTelCriteria extends ByStringCriteria {

    public TramitByIdTralTelCriteria(String entityAlias) {
        super(entityAlias + ".idTralTel");
    }

}
