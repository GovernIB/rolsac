package es.caib.rolsac.api.v2.formulari;

import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public interface FormulariQueryServiceStrategy {

    String getTitol(long id);
    
    TramitDTO obtenirTramit(long id, TramitCriteria tramitCriteria);

}
