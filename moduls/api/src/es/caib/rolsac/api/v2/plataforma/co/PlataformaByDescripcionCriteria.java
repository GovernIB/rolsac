package es.caib.rolsac.api.v2.plataforma.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlataformaByDescripcionCriteria extends ByStringCriteria {

    public PlataformaByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
