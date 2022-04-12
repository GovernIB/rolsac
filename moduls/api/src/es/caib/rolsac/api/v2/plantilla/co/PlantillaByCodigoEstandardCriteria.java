package es.caib.rolsac.api.v2.plantilla.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PlantillaByCodigoEstandardCriteria extends ByStringCriteria {

    public PlantillaByCodigoEstandardCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandard");
    }

}
