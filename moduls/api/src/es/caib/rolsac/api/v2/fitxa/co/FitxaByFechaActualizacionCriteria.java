package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class FitxaByFechaActualizacionCriteria extends ByDateCriteria {

    public FitxaByFechaActualizacionCriteria(String entityAlias) {
        super(entityAlias + ".fechaActualizacion");
    }

}
