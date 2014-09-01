package es.caib.rolsac.api.v2.espaiTerritorial.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EspaiTerritorialByNombreCriteria extends ByStringCriteria {

    public EspaiTerritorialByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
