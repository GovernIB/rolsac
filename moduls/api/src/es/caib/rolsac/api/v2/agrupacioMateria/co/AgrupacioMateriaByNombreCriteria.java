package es.caib.rolsac.api.v2.agrupacioMateria.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class AgrupacioMateriaByNombreCriteria extends ByStringCriteria {

    public AgrupacioMateriaByNombreCriteria(String i18nAlias) {
        super(i18nAlias + ".nombre");
    }

}
