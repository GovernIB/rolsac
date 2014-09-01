package es.caib.rolsac.api.v2.materiaAgrupacio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class MateriaAgrupacioByOrdenCriteria extends ByLongCriteria {

    public MateriaAgrupacioByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
