package es.caib.rolsac.api.v2.formulari.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class FormulariByUrlManualCriteria extends ByStringCriteria {
    
    public FormulariByUrlManualCriteria(String entityAlias) {
        super(entityAlias + ".urlManual");
    }

}
