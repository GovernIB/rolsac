package es.caib.rolsac.api.v2.documentoServicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class DocumentoServicioByServicioCriteria extends ByLongCriteria {

	public DocumentoServicioByServicioCriteria(String entityAlias) {
        super(entityAlias + ".servicio");
    }
}
