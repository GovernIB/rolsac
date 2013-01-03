package es.caib.rolsac.api.v2.excepcioDocumentacio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ExcepcioDocumentacioByDescripcionCriteria extends ByStringCriteria {
	
    public ExcepcioDocumentacioByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }
    
}
