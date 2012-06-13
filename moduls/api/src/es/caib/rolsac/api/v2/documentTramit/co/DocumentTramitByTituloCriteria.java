package es.caib.rolsac.api.v2.documentTramit.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentTramitByTituloCriteria extends ByStringCriteria {

    public DocumentTramitByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
