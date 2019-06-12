package org.ibit.rol.sac.persistence.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RolsacPropertiesUtil {
	private static Log log = LogFactory.getLog(RolsacPropertiesUtil.class);
	
	
	private static final String ERROR_MESSAGE = "Error obteniendo la propiedad ";
	

	private static String getProperty(String property) {
        try {
            return System.getProperty(property);
        } catch (Exception e) {
            log.error(ERROR_MESSAGE + property, e);
            return null;
        }
    }
	
	private static int getIntFromProperty(String property) {
        try {
            return Integer.parseInt(System.getProperty(property));
        } catch (Exception e) {
            log.error(ERROR_MESSAGE + property, e);
            return 0;
        }
    }
	
	public static final boolean getControlProporciones() {
        if (getProperty("es.caib.rolsac.fitxa.control.proporcions").equals("S")) {
            return true;
        } else {
            return false;
        }
    }
	
	public static final int getAltoIcono() {
		return getIntFromProperty("es.caib.rolsac.fitxa.icona.altura");
	}
	
	public static final int getAnchoIcono() {
		return getIntFromProperty("es.caib.rolsac.fitxa.icona.amplada");
	}
	
	public static final int getAltoBanner() {
		return getIntFromProperty("es.caib.rolsac.fitxa.banner.altura");
	}
	
	public static final int getAnchoBanner() {
		return getIntFromProperty("es.caib.rolsac.fitxa.banner.amplada");
	}
	public static final int getAltoImagen() {
		return getIntFromProperty("es.caib.rolsac.fitxa.imatge.altura");
	}
	
	public static final int getAnchoImagen() {
		return getIntFromProperty("es.caib.rolsac.fitxa.imatge.amplada");
	}
	
	
	/**
	 * funcion que retorna el código del público objetivo interno
	 * @return
	 */
	public static String getPropiedadPOInternoUrlProc() {
		String res = "";
		try {
			res = System.getProperty("es.caib.rolsac.procser.interno.url.procedimientos");
		}catch(Exception e) {
			 log.error(ERROR_MESSAGE + "es.caib.rolsac.procser.interno.url.procedimientos", e);
			res="";			
		}
		return res==null?"":res;		
	}	
	
	/**
	 * funcion que retorna el código del público objetivo interno
	 * @return
	 */
	public static String getPropiedadPOInternoUrlSer() {
		String res = "";
		
		try {
			res = System.getProperty("es.caib.rolsac.procser.interno.url.servicios");
		}catch(Exception e) {
			 log.error(ERROR_MESSAGE + "es.caib.rolsac.procser.interno.url.servicios", e);
			res="";			
		}
		return res==null?"":res;		
	}	
	
}
