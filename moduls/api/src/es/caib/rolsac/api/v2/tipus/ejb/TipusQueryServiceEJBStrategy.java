package es.caib.rolsac.api.v2.tipus.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceStrategy;

public class TipusQueryServiceEJBStrategy implements TipusQueryServiceStrategy {
    
    private TipusQueryServiceDelegate tipusQueryServiceDelegate;
    
    public void setTipusQueryServiceDelegate(TipusQueryServiceDelegate tipusQueryServiceDelegate) {
        this.tipusQueryServiceDelegate = tipusQueryServiceDelegate;
    }

    public List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria) {
        return tipusQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
    }

    public int getNumNormatives(Long id, TIPUS_NORMATIVA totes) {
        return tipusQueryServiceDelegate.llistarNormatives(id, totes);
    }

}
