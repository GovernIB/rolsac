package es.caib.rolsac.api.v2.usuari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UsuariByUsernameCriteria extends ByStringCriteria {

    public UsuariByUsernameCriteria(String entityAlias) {
        super(entityAlias + ".username");
    }

}
