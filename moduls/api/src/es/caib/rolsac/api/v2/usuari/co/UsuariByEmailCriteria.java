package es.caib.rolsac.api.v2.usuari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UsuariByEmailCriteria extends ByStringCriteria {

    public UsuariByEmailCriteria(String entityAlias) {
        super(entityAlias + ".email");
    }

}
