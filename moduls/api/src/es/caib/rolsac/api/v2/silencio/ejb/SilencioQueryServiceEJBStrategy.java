package es.caib.rolsac.api.v2.silencio.ejb;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.silencio.SilencioDTO;
import es.caib.rolsac.api.v2.silencio.SilencioQueryServiceStrategy;

public class SilencioQueryServiceEJBStrategy implements SilencioQueryServiceStrategy {
	
	private SilencioQueryServiceDelegate silencioQueryServiceDelegate;

    public void setSilencioQueryServiceDelegate(SilencioQueryServiceDelegate silencioQueryServiceDelegate) {
        this.silencioQueryServiceDelegate = silencioQueryServiceDelegate;
    }

	

	public SilencioDTO obtenirSilenci(String codSilencio, String idioma) throws StrategyException {
		
		try {
			
            return silencioQueryServiceDelegate.obtenirSilenci(codSilencio, idioma);
            
        } catch (DelegateException e) {
        	
            throw new StrategyException(e);
            
        }
		
	}
	
}
