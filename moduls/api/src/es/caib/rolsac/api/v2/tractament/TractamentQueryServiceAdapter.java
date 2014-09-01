package es.caib.rolsac.api.v2.tractament;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public class TractamentQueryServiceAdapter extends TractamentDTO implements TractamentQueryService {

    private static final long serialVersionUID = -2436577076981294154L;

    
    public TractamentQueryServiceAdapter(TractamentDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

}
