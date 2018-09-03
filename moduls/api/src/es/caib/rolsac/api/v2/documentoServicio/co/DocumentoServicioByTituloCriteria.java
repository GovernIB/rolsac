package es.caib.rolsac.api.v2.documentoServicio.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class DocumentoServicioByTituloCriteria extends ByStringCriteria {

    public DocumentoServicioByTituloCriteria(String i18nAlias) {
        super(i18nAlias + ".titulo");
    }

}
