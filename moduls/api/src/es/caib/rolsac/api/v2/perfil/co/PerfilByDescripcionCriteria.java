package es.caib.rolsac.api.v2.perfil.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PerfilByDescripcionCriteria extends ByStringCriteria {

    public PerfilByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
