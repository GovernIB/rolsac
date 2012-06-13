package es.caib.rolsac.api.v2.unitatMateria.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatMateriaByUnidadPrincipalCriteria extends ByStringCriteria {

    public UnitatMateriaByUnidadPrincipalCriteria(String entityAlias) {
        super(entityAlias + ".unidadPrincipal");
    }

}
