package es.caib.rolsac.api.v2.usuari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UsuariByNombreCriteria extends ByStringCriteria {

    public UsuariByNombreCriteria(String entityAlias) {
        super(entityAlias + ".nombre");
    }

}
