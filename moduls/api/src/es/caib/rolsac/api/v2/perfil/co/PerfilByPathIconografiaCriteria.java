package es.caib.rolsac.api.v2.perfil.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PerfilByPathIconografiaCriteria extends ByStringCriteria {

    public PerfilByPathIconografiaCriteria(String entityAlias) {
        super(entityAlias + ".pathIconografia");
    }

}
