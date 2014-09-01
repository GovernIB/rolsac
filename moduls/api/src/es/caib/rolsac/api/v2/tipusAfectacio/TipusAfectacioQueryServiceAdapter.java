package es.caib.rolsac.api.v2.tipusAfectacio;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public class TipusAfectacioQueryServiceAdapter extends TipusAfectacioDTO implements TipusAfectacioQueryService {

    private static final long serialVersionUID = 1678213263172254406L;

    public TipusAfectacioQueryServiceAdapter(TipusAfectacioDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }

}
