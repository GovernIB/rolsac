package es.caib.rolsac.api.v2.plantilla.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlantillaByNombreCriteria extends ByStringCriteria {

    public PlantillaByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
