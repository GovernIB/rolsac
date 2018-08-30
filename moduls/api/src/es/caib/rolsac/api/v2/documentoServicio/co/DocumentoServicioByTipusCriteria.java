package es.caib.rolsac.api.v2.documentoServicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentoServicioByTipusCriteria extends ByLongCriteria {

    public DocumentoServicioByTipusCriteria(String entityAlias) {
        super(entityAlias + ".tipus");
    }

}
