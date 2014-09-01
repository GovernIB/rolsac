package es.caib.rolsac.api.v2.tipusAfectacio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class TipusAfectacioByNombreCriteria extends ByStringCriteria {

    public TipusAfectacioByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
