package es.caib.rolsac.api.v2.iniciacio;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.exception.StrategyException;
import es.caib.rolsac.api.v2.general.BeanUtils;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.iniciacio.ejb.IniciacioQueryServiceEJBStrategy;

public class IniciacioQueryServiceAdapter extends IniciacioDTO implements IniciacioQueryService {

	private static final long serialVersionUID = -4098949602949596484L;
	
	private IniciacioQueryServiceStrategy iniciacioQueryServiceStrategy;
	
	public void setIniciacioQueryServiceStrategy(IniciacioQueryServiceStrategy iniciacioQueryServiceStrategy) {
		this.iniciacioQueryServiceStrategy = iniciacioQueryServiceStrategy;
	}
	
	public IniciacioQueryServiceAdapter(IniciacioDTO dto) throws QueryServiceException {
        
		try {
        
			PropertyUtils.copyProperties(this, dto);
        
        } catch (Exception e) {
        
        	throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
    
        }
	
	}
	
	private STRATEGY getStrategy() {
        return iniciacioQueryServiceStrategy instanceof IniciacioQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }

	public List<IniciacioQueryServiceAdapter> llistarTipusIniciacions(IniciacioCriteria iniciacioCriteria) throws QueryServiceException {
		
		try {
			
            List<IniciacioDTO> llistaDTO = iniciacioQueryServiceStrategy.llistarTipusIniciacions(iniciacioCriteria);
            List<IniciacioQueryServiceAdapter> llistaQueryServiceAdapter = new ArrayList<IniciacioQueryServiceAdapter>();
            
            for (IniciacioDTO iniciacioDTO : llistaDTO) {
                llistaQueryServiceAdapter.add((IniciacioQueryServiceAdapter)BeanUtils.getAdapter("iniciacio", getStrategy(), iniciacioDTO));
            }
            
            return llistaQueryServiceAdapter;
            
        } catch (StrategyException e) {
        	
            throw new QueryServiceException(ExceptionMessages.LIST_GETTER + "iniciacions.", e);
            
        }
		
	}

	public IniciacioQueryServiceAdapter obtenirTipusIniciacio(IniciacioCriteria iniciacioCriteria) throws QueryServiceException {
		
		try {
			
			IniciacioDTO dto = iniciacioQueryServiceStrategy.obtenirTipusIniciacio(iniciacioCriteria);
			return (IniciacioQueryServiceAdapter)BeanUtils.getAdapter("iniciacio", getStrategy(), dto);
			
		} catch (StrategyException e) {
			
            throw new QueryServiceException(ExceptionMessages.OBJECT_GETTER + "iniciacio.", e);
            
        }
		
	}
	
}
