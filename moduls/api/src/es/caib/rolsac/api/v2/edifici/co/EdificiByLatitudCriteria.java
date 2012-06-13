package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByLatitudCriteria extends ByStringCriteria {

    public EdificiByLatitudCriteria(String entityAlias) {
        super(entityAlias + ".latitud");
    }

}
