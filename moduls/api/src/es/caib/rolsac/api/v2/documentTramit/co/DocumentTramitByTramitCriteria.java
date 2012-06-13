package es.caib.rolsac.api.v2.documentTramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentTramitByTramitCriteria extends ByLongCriteria {

    public DocumentTramitByTramitCriteria(String entityAlias) {
        super(entityAlias + ".tramit");
    }

}
