package es.caib.rolsac.api.v2.espaiTerritorial.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EspaiTerritorialByCoordenadasCriteria extends ByStringCriteria {

    public EspaiTerritorialByCoordenadasCriteria(String entityAlias) {
        super(entityAlias + ".coordenadas");
    }

}
