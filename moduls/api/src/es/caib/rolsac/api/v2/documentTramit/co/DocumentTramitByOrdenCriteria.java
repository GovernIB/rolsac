package es.caib.rolsac.api.v2.documentTramit.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentTramitByOrdenCriteria extends ByLongCriteria {

    public DocumentTramitByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
