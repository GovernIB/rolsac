package es.caib.rolsac.api.v2.fetVital.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FetVitalByDescripcionCriteria extends ByStringCriteria {

    public FetVitalByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
