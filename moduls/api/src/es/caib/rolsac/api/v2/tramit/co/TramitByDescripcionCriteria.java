package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TramitByDescripcionCriteria extends ByStringCriteria {

    public TramitByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
