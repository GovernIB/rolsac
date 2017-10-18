package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByRequisitosCriteria extends ByStringCriteria {

    public ServicioByRequisitosCriteria(String i18nAlias) {
        super(i18nAlias + ".requisitos");
    }

}
