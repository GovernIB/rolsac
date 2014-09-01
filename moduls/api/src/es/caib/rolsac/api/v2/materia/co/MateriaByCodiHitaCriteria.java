package es.caib.rolsac.api.v2.materia.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class MateriaByCodiHitaCriteria extends ByStringCriteria {

    public MateriaByCodiHitaCriteria(String entityAlias) {
        super(entityAlias + ".codiHita");
    }

}
