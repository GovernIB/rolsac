package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByStringCriteria;

public class ProcedimentByResolucionCriteria extends ByStringCriteria {

    public ProcedimentByResolucionCriteria(String i18nAlias) {
        super(i18nAlias + ".resolucion");
    }

}
