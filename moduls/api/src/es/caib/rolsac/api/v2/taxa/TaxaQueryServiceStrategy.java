package es.caib.rolsac.api.v2.taxa;

import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public interface TaxaQueryServiceStrategy {

    TramitDTO obtenirTramit(long id, TramitCriteria tramitCriteria);

}
