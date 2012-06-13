package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByLongitudCriteria extends ByStringCriteria {

    public EdificiByLongitudCriteria(String entityAlias) {
        super(entityAlias + ".longitud");
    }

}
