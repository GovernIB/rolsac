package es.caib.rolsac.api.v2.documentTramit.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class DocumentTramitQueryServiceEJB {

    public TramitDTO obtenirTramit(long idTramit) {
        TramitCriteria criteria = new TramitCriteria();
        criteria.setId(String.valueOf(idTramit));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirTramit(criteria);
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) {
        return EJBUtils.getArxiuDTO(idArxiu);
    }
    
}
