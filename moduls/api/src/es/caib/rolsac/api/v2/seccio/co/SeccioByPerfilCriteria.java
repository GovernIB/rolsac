package es.caib.rolsac.api.v2.seccio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class SeccioByPerfilCriteria extends ByStringCriteria {

    public SeccioByPerfilCriteria(String entityAlias) {
        super(entityAlias + ".perfil");
    }

}
