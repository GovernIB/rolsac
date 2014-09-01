package es.caib.rolsac.api.v2.excepcioDocumentacio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ExcepcioDocumentacioByNombreCriteria extends ByStringCriteria {
	public ExcepcioDocumentacioByNombreCriteria(String i18nAlias) {
		super(i18nAlias + ".nombre");
	}
}