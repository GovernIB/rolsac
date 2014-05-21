package es.caib.rolsac.api.v2.idioma;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;
import es.caib.rolsac.api.v2.general.BeanUtils.STRATEGY;
import es.caib.rolsac.api.v2.idioma.ejb.IdiomaQueryServiceEJBStrategy;

public class IdiomaQueryServiceAdapter extends IdiomaDTO implements IdiomaQueryService {

	private static final long serialVersionUID = 1L;
	
	private IdiomaQueryServiceStrategy idiomaQueryServiceStrategy;

    public void setIdiomaQueryServiceStrategy(IdiomaQueryServiceStrategy idiomaQueryServiceStrategy) {
        this.idiomaQueryServiceStrategy = idiomaQueryServiceStrategy;
    }

    public IdiomaQueryServiceAdapter(IdiomaDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

    public STRATEGY getStrategy() {
        return idiomaQueryServiceStrategy instanceof IdiomaQueryServiceEJBStrategy ? STRATEGY.EJB : STRATEGY.WS;
    }
	
}
