package es.caib.rolsac.api.v2.taxa;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.taxa.ejb.TaxaQueryServiceEJBStrategy;
import es.caib.rolsac.api.v2.tramit.TramitQueryServiceAdapter;

public class TaxaQueryServiceAdapter extends TaxaDTO implements TaxaQueryService {

    private static final long serialVersionUID = 884729428608351944L;

    private TaxaQueryServiceStrategy taxaQueryServiceStrategy;    
    
    public void setTaxaQueryServiceStrategy(TaxaQueryServiceStrategy taxaQueryServiceStrategy) {
        this.taxaQueryServiceStrategy = taxaQueryServiceStrategy;
    }
    
    private String rolsacUrl;
	public void setRolsacUrl(String rolsacUrl) {
		this.rolsacUrl = rolsacUrl;
		if (this.taxaQueryServiceStrategy != null) {
			this.taxaQueryServiceStrategy.setUrl(rolsacUrl);
		}
	}

    public TaxaQueryServiceAdapter(TaxaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private STRATEGY getStrategy() {
        return taxaQueryServiceStrategy instanceof TaxaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
    
    public TramitQueryServiceAdapter obtenirTramit() throws QueryServiceException {
        if (this.getTramit() == null) {return null;}
        try {
        	TramitQueryServiceAdapter tqsa = (TramitQueryServiceAdapter) BeanUtils.getAdapter("tramit", getStrategy(), taxaQueryServiceStrategy.obtenirTramit(this.getTramit()));
        	if (tqsa != null && rolsacUrl != null) {
        		tqsa.setRolsacUrl(rolsacUrl);
        	}
        	return tqsa;
        } catch (StrategyException e) {
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "tramite.", e);
        }
    }


}
