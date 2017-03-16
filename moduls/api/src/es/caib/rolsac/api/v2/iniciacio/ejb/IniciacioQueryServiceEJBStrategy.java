package es.caib.rolsac.api.v2.iniciacio.ejb;

import java.util.List;

import es.caib.rolsac.api.v2.exception.DelegateException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.iniciacio.IniciacioCriteria;
import es.caib.rolsac.api.v2.iniciacio.IniciacioDTO;
import es.caib.rolsac.api.v2.iniciacio.IniciacioQueryServiceStrategy;

public class IniciacioQueryServiceEJBStrategy implements IniciacioQueryServiceStrategy {
	
	private IniciacioQueryServiceDelegate iniciacioQueryServiceDelegate;

    public void setIniciacioQueryServiceDelegate(IniciacioQueryServiceDelegate iniciacioQueryServiceDelegate) {
        this.iniciacioQueryServiceDelegate = iniciacioQueryServiceDelegate;
    }

	public List<IniciacioDTO> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws StrategyException {
		
		try {
			
            return iniciacioQueryServiceDelegate.llistarTipusIniciacions(iniciacioCriteria);
            
        } catch (DelegateException e) {
        	
            throw new StrategyException(e);
            
        }
		
	}

	public IniciacioDTO obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws StrategyException {
		
		try {
			
            return iniciacioQueryServiceDelegate.obtenirTipusIniciacio(iniciacioCriteria);
            
        } catch (DelegateException e) {
        	
            throw new StrategyException(e);
            
        }
		
	}

	public void setUrl(String url) {
		//No es necesario en EJBs.
	}
	
}
