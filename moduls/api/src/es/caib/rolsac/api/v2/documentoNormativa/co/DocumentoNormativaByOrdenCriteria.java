package es.caib.rolsac.api.v2.documentoNormativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentoNormativaByOrdenCriteria extends ByLongCriteria {

    public DocumentoNormativaByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
