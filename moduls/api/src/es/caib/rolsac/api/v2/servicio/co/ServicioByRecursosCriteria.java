package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByRecursosCriteria extends ByStringCriteria {

    public ServicioByRecursosCriteria(String i18nAlias) {
        super(i18nAlias + ".recursos");
    }

}
