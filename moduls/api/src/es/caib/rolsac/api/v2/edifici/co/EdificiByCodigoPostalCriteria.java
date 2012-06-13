package es.caib.rolsac.api.v2.edifici.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class EdificiByCodigoPostalCriteria extends ByStringCriteria {

    public EdificiByCodigoPostalCriteria(String entityAlias) {
        super(entityAlias + ".codigoPostal");
    }

}
