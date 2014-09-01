package es.caib.rolsac.api.v2.tipus.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;
import es.caib.rolsac.api.v2.tipus.TipusQueryServiceStrategy;

public class TipusQueryServiceEJBStrategy implements TipusQueryServiceStrategy {
    
    private TipusQueryServiceDelegate tipusQueryServiceDelegate;
    
    public void setTipusQueryServiceDelegate(TipusQueryServiceDelegate tipusQueryServiceDelegate) {
        this.tipusQueryServiceDelegate = tipusQueryServiceDelegate;
    }

    public List<NormativaDTO> llistarNormatives(Long id, NormativaCriteria normativaCriteria) throws StrategyException {
        try {
            return tipusQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumNormatives(Long id, TIPUS_NORMATIVA totes) throws StrategyException {
        try {
            return tipusQueryServiceDelegate.getNumNormatives(id, totes);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

}
