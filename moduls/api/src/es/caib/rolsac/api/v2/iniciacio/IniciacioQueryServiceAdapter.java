package es.caib.rolsac.api.v2.iniciacio;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public class IniciacioQueryServiceAdapter extends IniciacioDTO implements IniciacioQueryService {

	private static final long serialVersionUID = -4098949602949596484L;
	
	public IniciacioQueryServiceAdapter(IniciacioDTO dto) throws QueryServiceException {
        
		try {
        
			PropertyUtils.copyProperties(this, dto);
        
        } catch (Exception e) {
        
        	throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
    
        }
	
	}

}
