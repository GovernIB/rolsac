package es.caib.rolsac.api.v2.plantilla.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlantillaByUrlAccesoCriteria extends ByStringCriteria {

    public PlantillaByUrlAccesoCriteria(String entityAlias) {
        super(entityAlias + ".pathIconografia");
    }

}
