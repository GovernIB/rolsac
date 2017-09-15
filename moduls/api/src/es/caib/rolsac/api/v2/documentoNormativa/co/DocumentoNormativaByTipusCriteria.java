package es.caib.rolsac.api.v2.documentoNormativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentoNormativaByTipusCriteria extends ByLongCriteria {

    public DocumentoNormativaByTipusCriteria(String entityAlias) {
        super(entityAlias + ".tipus");
    }

}
