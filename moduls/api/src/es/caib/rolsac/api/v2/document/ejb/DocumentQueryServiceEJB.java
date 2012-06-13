package es.caib.rolsac.api.v2.document.ejb;

import es.caib.rolsac.api.v2.arxiu.ArxiuDTO;
import es.caib.rolsac.api.v2.fitxa.FitxaCriteria;
import es.caib.rolsac.api.v2.fitxa.FitxaDTO;
import es.caib.rolsac.api.v2.general.EJBUtils;
import es.caib.rolsac.api.v2.procediment.ProcedimentCriteria;
import es.caib.rolsac.api.v2.procediment.ProcedimentDTO;
import es.caib.rolsac.api.v2.rolsac.ejb.RolsacQueryServiceEJB;

public class DocumentQueryServiceEJB {

    public FitxaDTO obtenirFitxa(long idFitxa) {
        FitxaCriteria criteria = new FitxaCriteria();
        criteria.setId(String.valueOf(idFitxa));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirFitxa(criteria);
    }

    public ProcedimentDTO obtenirProcediment(long idProc) {
        ProcedimentCriteria criteria = new ProcedimentCriteria();
        criteria.setId(String.valueOf(idProc));
        RolsacQueryServiceEJB ejb = new RolsacQueryServiceEJB(); 
        return ejb.obtenirProcediment(criteria);
    }

    public ArxiuDTO obtenirArxiu(long idArxiu) {
        return EJBUtils.getArxiuDTO(idArxiu);
    }
    
}
