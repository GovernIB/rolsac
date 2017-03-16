package es.caib.rolsac.api.v2.butlleti.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.butlleti.ButlletiQueryServiceStrategy;
import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.normativa.NormativaCriteria;
import es.caib.rolsac.api.v2.normativa.NormativaDTO;
import es.caib.rolsac.api.v2.normativa.NormativaQueryService.TIPUS_NORMATIVA;

public class ButlletiQueryServiceEJBStrategy implements ButlletiQueryServiceStrategy {

    private ButlletiQueryServiceDelegate butlletiQueryServiceDelegate;
    
    public void setButlletiQueryServiceDelegate(ButlletiQueryServiceDelegate butlletiQueryServiceDelegate) {
        this.butlletiQueryServiceDelegate = butlletiQueryServiceDelegate;
    }
    
    public List<NormativaDTO> llistarNormatives(long id, NormativaCriteria normativaCriteria) throws StrategyException {
        try {
            return butlletiQueryServiceDelegate.llistarNormatives(id, normativaCriteria);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

    public int getNumNormatives(long id, TIPUS_NORMATIVA tipus) throws StrategyException {
        try {
            return butlletiQueryServiceDelegate.getNumNormatives(id, tipus);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No necesario en EJB.
	}

}
