package es.caib.rolsac.api.v2.documentoServicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentoServicioByOrdenCriteria extends ByLongCriteria {

    public DocumentoServicioByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".orden");
    }

}
