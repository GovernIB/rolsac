package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class UnitatAdministrativaByClaveHitaCriteria extends ByStringCriteria {

    public UnitatAdministrativaByClaveHitaCriteria(String entityAlias) {
        super(entityAlias + ".claveHita");
    }

}
