package es.caib.rolsac.api.v2.formulari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FormulariByNombreCriteria extends ByStringCriteria {
    
    public FormulariByNombreCriteria(String entityAlias) {
        super(entityAlias + ".nombre");
    }

}
