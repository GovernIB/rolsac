package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByDescripcionCriteria extends ByStringCriteria {

    public EdificiByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
