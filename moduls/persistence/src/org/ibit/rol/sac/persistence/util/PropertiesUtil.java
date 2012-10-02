package org.ibit.rol.sac.persistence.util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtil {
    
    protected static Log log = LogFactory.getLog(PropertiesUtil.class);  
	
	private final String path;
	
	public PropertiesUtil(String path) {
		this.path = path;
	}

	public String obtieneValor(String valor) {
		String resultado = null;
		try {
			/*
			 * MUY IMPORTANTE!!!!!, EL NOMBRE DEL FICHERO DEBE CONTENER TODO EL PATH DE PAQUETES!!!
			 */
			Locale currentLocale = new Locale("es", "ES");
			ResourceBundle labels = ResourceBundle.getBundle(path,currentLocale);
			resultado = labels.getString(valor);
		} catch (MissingResourceException e) {
			log.error(e.toString());
		}
		return resultado;
	}
}