package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByNotificacionCriteria extends ByStringCriteria {

    public ProcedimentByNotificacionCriteria(String i18nAlias) {
        super(i18nAlias + ".notificacion");
    }

}
