package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByFaxCriteria extends ByStringCriteria {

    public EdificiByFaxCriteria(String entityAlias) {
        super(entityAlias + ".fax");
    }

}
