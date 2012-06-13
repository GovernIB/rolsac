package es.caib.rolsac.api.v2.documentTramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentTramitByDescripcionCriteria extends ByStringCriteria {

    public DocumentTramitByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
