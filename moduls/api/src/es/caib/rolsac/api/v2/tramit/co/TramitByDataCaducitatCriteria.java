package es.caib.rolsac.api.v2.tramit.co;

import es.caib.rolsac.api.v2.general.co.ByDateCriteria;

public class TramitByDataCaducitatCriteria extends ByDateCriteria {

    public TramitByDataCaducitatCriteria(String entityAlias) {
        super(entityAlias + ".dataCaducitat");
    }

}
