package es.caib.rolsac.api.v2.documentoNormativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentoNormativaByNormativaCriteria extends ByLongCriteria {

    public DocumentoNormativaByNormativaCriteria(String entityAlias) {
        super(entityAlias + ".normativa");
    }

}
