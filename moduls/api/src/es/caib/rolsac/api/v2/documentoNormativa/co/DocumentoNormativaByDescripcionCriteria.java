package es.caib.rolsac.api.v2.documentoNormativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentoNormativaByDescripcionCriteria extends ByStringCriteria {

    public DocumentoNormativaByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
