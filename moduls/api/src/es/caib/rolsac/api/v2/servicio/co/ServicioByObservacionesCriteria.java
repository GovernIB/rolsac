package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByObservacionesCriteria extends ByStringCriteria {

    public ServicioByObservacionesCriteria(String i18nAlias) {
        super(i18nAlias + ".observaciones");
    }

}
