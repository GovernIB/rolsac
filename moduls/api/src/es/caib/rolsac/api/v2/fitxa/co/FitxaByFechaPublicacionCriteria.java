package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class FitxaByFechaPublicacionCriteria extends ByDateCriteria {

    public FitxaByFechaPublicacionCriteria(String entityAlias) {
        super(entityAlias + ".fechaPublicacion");
    }

}
