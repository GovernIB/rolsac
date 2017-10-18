package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ServicioByResultatCriteria extends ByStringCriteria {

    public ServicioByResultatCriteria(String i18nAlias) {
        super(i18nAlias + ".resultat");
    }

}
