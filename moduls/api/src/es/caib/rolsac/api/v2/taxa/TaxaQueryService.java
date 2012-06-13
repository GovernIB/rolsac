package es.caib.rolsac.api.v2.taxa;

import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;

public interface TaxaQueryService {

    TramitQueryService obtenirTramit(TramitCriteria tramitCriteria);

}
