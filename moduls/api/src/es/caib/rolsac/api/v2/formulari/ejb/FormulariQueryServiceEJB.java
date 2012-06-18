package es.caib.rolsac.api.v2.formulari.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;
import es.caib.rolsac.api.v2.tramit.TramitCriteria;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class FormulariQueryServiceEJB {

    public ArxiuDTO obtenirArchivo(Long idArchivo) {
        return EJBUtils.getArxiuDTO(idArchivo);
    }

    public ArxiuDTO obtenirManual(Long idManual) {
        return EJBUtils.getArxiuDTO(idManual);
    }

    public TramitDTO obtenirTramit(Long idTramit) {
        TramitCriteria tramitCriteria = new TramitCriteria();
        tramitCriteria.setId(String.valueOf(idTramit));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB();        
        return ejb.obtenirTramit(tramitCriteria);
    }

}
