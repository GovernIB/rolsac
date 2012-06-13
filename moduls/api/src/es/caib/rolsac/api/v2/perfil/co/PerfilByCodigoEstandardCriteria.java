package es.caib.rolsac.api.v2.perfil.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PerfilByCodigoEstandardCriteria extends ByStringCriteria {

    public PerfilByCodigoEstandardCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandard");
    }

}
