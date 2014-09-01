package es.caib.rolsac.api.v2.catalegDocuments.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class CatalegDocumentsByOrdenCriteria extends ByLongCriteria {

    public CatalegDocumentsByOrdenCriteria(String entityAlias) {
        super(entityAlias + ".ordre");
    }		
	
}
