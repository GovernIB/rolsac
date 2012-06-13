package es.caib.rolsac.api.v2.materia.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class MateriaByCodigoEstandarCriteria extends ByStringCriteria {

    public MateriaByCodigoEstandarCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandar");
    }

}
