package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByNotificacionCriteria extends ByStringCriteria {

    public ServicioByNotificacionCriteria(String i18nAlias) {
        super(i18nAlias + ".notificacion");
    }

}
