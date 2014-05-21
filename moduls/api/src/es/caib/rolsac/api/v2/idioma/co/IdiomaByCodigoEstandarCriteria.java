package es.caib.rolsac.api.v2.idioma.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class IdiomaByCodigoEstandarCriteria extends ByStringCriteria {

    public IdiomaByCodigoEstandarCriteria(String i18nAlias) {
        super(i18nAlias + ".codigoEstandar");
    }

}
