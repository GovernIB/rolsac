package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByDireccionCriteria extends ByStringCriteria {

    public EdificiByDireccionCriteria(String entityAlias) {
        super(entityAlias + ".direccion");
    }

}
