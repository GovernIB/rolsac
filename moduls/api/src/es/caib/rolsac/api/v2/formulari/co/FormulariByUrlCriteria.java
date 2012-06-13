package es.caib.rolsac.api.v2.formulari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FormulariByUrlCriteria extends ByStringCriteria {
    
    public FormulariByUrlCriteria(String entityAlias) {
        super(entityAlias + ".url");
    }

}
