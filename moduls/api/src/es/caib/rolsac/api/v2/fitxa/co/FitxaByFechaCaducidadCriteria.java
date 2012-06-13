package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class FitxaByFechaCaducidadCriteria extends ByDateCriteria {

    public FitxaByFechaCaducidadCriteria(String entityAlias) {
        super(entityAlias + ".fechaCaducidad");
    }

}
