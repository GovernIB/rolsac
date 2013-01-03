package es.caib.rolsac.api.v2.catalegDocuments.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class CatalegDocumentsByDescripcionCriteria extends ByStringCriteria {
	
    public CatalegDocumentsByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }
	
}
