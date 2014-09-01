package es.caib.rolsac.api.v2.materia.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class MateriaByNombreCriteria extends ByStringCriteria {

    public MateriaByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
