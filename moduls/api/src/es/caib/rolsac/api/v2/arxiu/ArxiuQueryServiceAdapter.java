package es.caib.rolsac.api.v2.arxiu;

import org.apache.commons.beanutils.PropertyUtils;

public class ArxiuQueryServiceAdapter extends ArxiuDTO implements ArxiuQueryService {
	
    public ArxiuQueryServiceAdapter(ArxiuDTO dto) {
        try {
            PropertyUtils.copyProperties(this, dto);
        } catch (Exception e) {
            e.printStackTrace(); // FIXME: log.error...
        }
    }
    
    public String recuperarURL(){
                
        //datos de ejemplo
        //return System.getProperty("ruta").replaceAll("%id%", String.valueOf(this.getId()));
        //return System.getProperty("ruta") + String.valueOf(this.getId());
        
        return null;
    }
	
}
