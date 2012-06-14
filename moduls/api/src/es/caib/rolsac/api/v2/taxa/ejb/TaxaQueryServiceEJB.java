package es.caib.rolsac.api.v2.taxa.ejb;

import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceEJB {

    public TramitDTO obtenirTramit(long idTramit) {
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId(String.valueOf(idTramit));
        return ejb.obtenirTramit(tramitCriteria);
    }

}
