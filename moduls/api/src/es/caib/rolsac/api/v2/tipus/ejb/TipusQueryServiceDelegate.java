package es.caib.rolsac.api.v2.tipus.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class TipusQueryServiceDelegate {

    private TipusQueryServiceEJBLocator tipusQueryServiceLocator;    
    
    public void setTipusQueryServiceLocator(TipusQueryServiceEJBLocator tipusQueryServiceLocator) {
        this.tipusQueryServiceLocator = tipusQueryServiceLocator;
    }

    public List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria) {
        TipusQueryServiceEJB ejb = tipusQueryServiceLocator.getTipusQueryServiceEJB();
        return ejb.llistarNormatives(id, normativaCriteria);
    }

    public int llistarNormatives(Long id, TIPUS_NORMATIVA totes) {
        TipusQueryServiceEJB ejb = tipusQueryServiceLocator.getTipusQueryServiceEJB();
        return ejb.getNumNormatives(id, totes);
    }

}
