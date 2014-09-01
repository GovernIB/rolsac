package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByEmailCriteria extends ByStringCriteria {

    public EdificiByEmailCriteria(String entityAlias) {
        super(entityAlias + ".email");
    }

}
