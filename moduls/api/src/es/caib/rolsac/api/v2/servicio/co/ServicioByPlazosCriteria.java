package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByPlazosCriteria extends ByStringCriteria {

    public ServicioByPlazosCriteria(String i18nAlias) {
        super(i18nAlias + ".plazos");
    }

}
