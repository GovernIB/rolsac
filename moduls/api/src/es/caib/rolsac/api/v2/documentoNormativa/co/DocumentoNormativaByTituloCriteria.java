package es.caib.rolsac.api.v2.documentoNormativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentoNormativaByTituloCriteria extends ByStringCriteria {

    public DocumentoNormativaByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
