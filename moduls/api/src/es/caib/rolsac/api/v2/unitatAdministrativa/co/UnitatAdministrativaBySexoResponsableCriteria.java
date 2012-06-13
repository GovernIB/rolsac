package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaBySexoResponsableCriteria extends ByStringCriteria {

    public UnitatAdministrativaBySexoResponsableCriteria(String entityAlias) {
        super(entityAlias + ".sexoResponsable");
    }

}
