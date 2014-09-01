package es.caib.rolsac.api.v2.agrupacioFetVital.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class AgrupacioFetVitalByNombreCriteria extends ByStringCriteria {

    public AgrupacioFetVitalByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
