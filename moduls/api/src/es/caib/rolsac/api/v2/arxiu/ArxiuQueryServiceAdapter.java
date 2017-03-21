package es.caib.rolsac.api.v2.arxiu;

import org.apache.commons.beanutils.PropertyUtils;

import es.caib.rolsac.api.v2.exception.ExceptionMessages;
import es.caib.rolsac.api.v2.exception.QueryServiceException;

public class ArxiuQueryServiceAdapter extends ArxiuDTO implements ArxiuQueryService {

    private static final long serialVersionUID = 3801761110545312286L;
    
    /**
     * Parte final de la URL para obtener archivos.
     * La URL completa seria protocolo://ip:puerto + ARXIU_CONTEXTPATH_URL + idArchivo
     */
    public static final String ARXIU_CONTEXTPATH_URL = "/sacws-api/arxiu/apiArxiuServlet?id=";
    public static final String ARXIU_INTERN_CONTEXTPATH_URL = "/sacws-api/arxiu/apiArxiuInternServlet?id=";

    public ArxiuQueryServiceAdapter(ArxiuDTO dto) throws QueryServiceException {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            throw new QueryServiceException(ExceptionMessages.ADAPTER_CONSTRUCTOR, e);
        }
    }
    
    private String rolsacUrl;
    public void setRolsacUrl(String rolsacUrl) {
    	this.rolsacUrl = rolsacUrl;
    }

}
