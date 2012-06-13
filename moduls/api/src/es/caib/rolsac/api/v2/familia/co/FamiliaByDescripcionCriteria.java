package es.caib.rolsac.api.v2.familia.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FamiliaByDescripcionCriteria extends ByStringCriteria {

    public FamiliaByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
