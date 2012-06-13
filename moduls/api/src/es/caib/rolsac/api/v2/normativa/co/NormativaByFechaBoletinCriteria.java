package es.caib.rolsac.api.v2.normativa.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class NormativaByFechaBoletinCriteria extends ByDateCriteria {

    public NormativaByFechaBoletinCriteria(String entityAlias) {
        super(entityAlias + ".fechaBoletin");
    }

}
