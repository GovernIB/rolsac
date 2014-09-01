package es.caib.rolsac.api.v2.materia.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class MateriaByPalabrasClaveCriteria extends ByStringCriteria {

    public MateriaByPalabrasClaveCriteria(String i18nAlias) {
        super(i18nAlias + ".palabrasclave");
    }

}
