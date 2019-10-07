package es.caib.rolsac.api.v2.enllactelematico.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EnllacTelematicoByEnlaceCriteria extends ByStringCriteria {

    public EnllacTelematicoByEnlaceCriteria(String i18nAlias) {
        super(i18nAlias + ".enlace");
    }

}
