package es.caib.rolsac.api.v2.catalegDocuments.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class CatalegDocumentsByNombreCirteria extends ByStringCriteria {
		
	public CatalegDocumentsByNombreCirteria(String i18nAlias) {
	    super(i18nAlias + ".nombre");
	}

}