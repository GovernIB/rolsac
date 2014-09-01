package es.caib.rolsac.api.v2.espaiTerritorial.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class EspaiTerritorialByNivelCriteria extends ByLongCriteria {

    public EspaiTerritorialByNivelCriteria(String entityAlias) {
        super(entityAlias + ".nivel");
    }

}
