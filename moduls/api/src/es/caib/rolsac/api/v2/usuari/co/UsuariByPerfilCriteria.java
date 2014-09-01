package es.caib.rolsac.api.v2.usuari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UsuariByPerfilCriteria extends ByStringCriteria {

    public UsuariByPerfilCriteria(String entityAlias) {
        super(entityAlias + ".perfil");
    }

}
