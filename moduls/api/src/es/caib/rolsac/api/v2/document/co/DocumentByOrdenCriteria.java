package es.caib.rolsac.api.v2.document.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentByOrdenCriteria extends ByLongCriteria {

    public DocumentByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
