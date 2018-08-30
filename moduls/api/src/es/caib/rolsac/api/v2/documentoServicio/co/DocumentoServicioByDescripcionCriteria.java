package es.caib.rolsac.api.v2.documentoServicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentoServicioByDescripcionCriteria extends ByStringCriteria {

    public DocumentoServicioByDescripcionCriteria(String i18nAlias) {
        super(i18nAlias + ".descripcion");
    }

}
