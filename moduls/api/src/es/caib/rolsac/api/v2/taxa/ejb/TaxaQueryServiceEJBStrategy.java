package es.caib.rolsac.api.v2.taxa.ejb;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.taxa.TaxaQueryServiceStrategy;
import es.caib.rolsac.api.v2.tramit.TramitDTO;

public class TaxaQueryServiceEJBStrategy implements TaxaQueryServiceStrategy {

    TaxaQueryServiceDelegate taxaQueryServiceDelegate;
    
    public void setTaxaQueryServiceDelegate(TaxaQueryServiceDelegate taxaQueryServiceDelegate) {
        this.taxaQueryServiceDelegate = taxaQueryServiceDelegate;
    }

    public TramitDTO obtenirTramit(long idTramit) throws StrategyException {
        try {
            return taxaQueryServiceDelegate.obtenirTramit(idTramit);
        } catch (DelegateException e) {
            throw new StrategyException(e);
        }
    }

	public void setUrl(String url) {
		//No es necesario en EJBs.
	}

}
