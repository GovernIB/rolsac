package es.caib.rolsac.api.v2.plataforma.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlataformaByNombreCriteria extends ByStringCriteria {

    public PlataformaByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
