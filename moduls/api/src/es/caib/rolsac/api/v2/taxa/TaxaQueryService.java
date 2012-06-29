package es.caib.rolsac.api.v2.taxa;

import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public interface TaxaQueryService {

    public TramitQueryServiceAdapter obtenirTramit() throws QueryServiceException;

}
