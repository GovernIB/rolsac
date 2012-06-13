package es.caib.rolsac.api.v2.formulari;

import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitQueryService;

public interface FormulariQueryService {

    TramitQueryService obtenirTramit(TramitCriteria tramitCriteria);
    
    // Archivo manual
//    Archivo archivo

}
