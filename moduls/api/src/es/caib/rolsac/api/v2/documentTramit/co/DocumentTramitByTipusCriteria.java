package es.caib.rolsac.api.v2.documentTramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentTramitByTipusCriteria extends ByLongCriteria {

    public DocumentTramitByTipusCriteria(String entityAlias) {
        super(entityAlias + ".tipus");
    }

}
