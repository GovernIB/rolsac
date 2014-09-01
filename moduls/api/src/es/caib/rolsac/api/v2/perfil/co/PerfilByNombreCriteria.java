package es.caib.rolsac.api.v2.perfil.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class PerfilByNombreCriteria extends ByStringCriteria {

    public PerfilByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
