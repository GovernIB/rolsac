package es.caib.rolsac.api.v2.plantilla.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlantillaByDescripcionCriteria extends ByStringCriteria {

    public PlantillaByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
