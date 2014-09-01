package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByTelefonoCriteria extends ByStringCriteria {

    public EdificiByTelefonoCriteria(String entityAlias) {
        super(entityAlias + ".telefono");
    }

}
