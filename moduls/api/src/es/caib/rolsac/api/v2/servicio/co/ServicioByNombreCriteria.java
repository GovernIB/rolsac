package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByNombreCriteria extends ByStringCriteria {

    public ServicioByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
