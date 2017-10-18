package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByResumenCriteria extends ByStringCriteria {

    public ServicioByResumenCriteria(String i18nAlias) {
        super(i18nAlias + ".resumen");
    }

}
