package es.caib.rolsac.api.v2.silencio;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.silencio.ejb.SilencioQueryServiceEJBStrategy;

public class SilencioQueryServiceAdapter extends SilencioDTO implements SilencioQueryService {

	private static final long serialVersionUID = -4098949602949596484L;
	
	private SilencioQueryServiceStrategy silencioQueryServiceStrategy;
	
	public void setSilencioQueryServiceStrategy(SilencioQueryServiceStrategy silencioQueryServiceStrategy) {
		this.silencioQueryServiceStrategy = silencioQueryServiceStrategy;
	}
	
	private String url;
	public void setRolsacUrl(String url) {
		this.url = url;
		if (this.silencioQueryServiceStrategy != null) {
			this.silencioQueryServiceStrategy.setUrl(url);
		}
	}
	
	public SilencioQueryServiceAdapter(SilencioDTO dto) throws QueryServiceException {
        
		try {
        
			PropertyUtils.copyProperties(this, dto);
        
        } catch (Exception e) {
        
        	throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
    
        }
	
	}
	
	private STRATEGY getStrategy() {
        return silencioQueryServiceStrategy instanceof SilencioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

	

	

	public SilencioQueryServiceAdapter obtenirSilencio(Long codSilencio, String idioma) throws QueryServiceException {
		try {
				
				SilencioDTO dto = silencioQueryServiceStrategy.obtenirSilenci(codSilencio, idioma);
				SilencioQueryServiceAdapter sqsa= (SilencioQueryServiceAdapter)BeanUtils.getAdapter("silencio", getStrategy(), dto);
				if (sqsa != null && url != null) {
					sqsa.setRolsacUrl(url);
				}
				return sqsa;
			} catch (StrategyException e) {
				
	            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "silencio.", e);
	            
	        }
		}

	
	
}
