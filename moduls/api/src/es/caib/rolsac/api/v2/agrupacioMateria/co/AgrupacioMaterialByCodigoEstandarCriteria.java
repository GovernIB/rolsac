package es.caib.rolsac.api.v2.agrupacioMateria.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class AgrupacioMaterialByCodigoEstandarCriteria extends ByStringCriteria {

    public AgrupacioMaterialByCodigoEstandarCriteria(String entityAlias) {
        super(entityAlias + ".codigoEstandar");
    }

}
