package es.caib.rolsac.api.v2.catalegDocuments.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;

public class CatalegDocumentsByAdmResponsableCriteria extends ByLongCriteria {
	
    public CatalegDocumentsByAdmResponsableCriteria(String entityAlias) {
        super(entityAlias + ".admResponsable");
    }
}
