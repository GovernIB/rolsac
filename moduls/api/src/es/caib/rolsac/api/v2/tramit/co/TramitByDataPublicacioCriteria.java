package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class TramitByDataPublicacioCriteria extends ByDateCriteria {

    public TramitByDataPublicacioCriteria(String entityAlias) {
        super(entityAlias + ".dataPublicacio");
    }

}
