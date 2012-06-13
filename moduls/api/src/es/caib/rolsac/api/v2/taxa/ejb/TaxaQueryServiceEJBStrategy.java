package es.caib.rolsac.api.v2.taxa.ejb;

import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceEJBStrategy implements TaxaQueryServiceStrategy {

    TaxaQueryServiceLocator tramitQueryServiceLocator;
    TaxaQueryServiceDelegate tramitQueryServiceDelegate;

    public TramitDTO obtenirTramit(long id, TramitCriteria tramitCriteria) {
        // TODO Auto-generated method stub
        return null;
    }

}
