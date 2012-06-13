package es.caib.rolsac.api.v2.publicObjectiu.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PublicObjectiuByTituloCriteria extends ByStringCriteria {

    public PublicObjectiuByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
