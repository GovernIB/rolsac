package es.caib.rolsac.api.v2.materia.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class MateriaByDestacadaCriteria extends ByStringCriteria {

    public MateriaByDestacadaCriteria(String entityAlias) {
        super(entityAlias + ".destacada");
    }

}
