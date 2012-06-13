package es.caib.rolsac.api.v2.document.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentByTituloCriteria extends ByStringCriteria {

    public DocumentByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
