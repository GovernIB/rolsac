package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByPoblacionCriteria extends ByStringCriteria {

    public EdificiByPoblacionCriteria(String entityAlias) {
        super(entityAlias + ".poblacion");
    }

}
