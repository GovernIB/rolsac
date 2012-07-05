package es.caib.rolsac.api.v2.arxiu;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public class ArxiuQueryServiceAdapter extends ArxiuDTO implements ArxiuQueryService {

    private static final long serialVersionUID = 3801761110545312286L;

    public ArxiuQueryServiceAdapter(ArxiuDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

}
