package es.caib.rolsac.api.v2.document.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentByDescripcionCriteria extends ByStringCriteria {

    public DocumentByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
