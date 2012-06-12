package es.caib.rolsac.back2.util;

public class HtmlUtils {

	/**
	 * De un string que contiene un enlace HTML extrae el t�tulo del enlace.
	 * @param texto String que contiene el enlace.
	 * @return T�tulo del enlace, si no hay enlace devuelve el texto tal cual.
	 */
	public static String obtenerTituloDeEnlaceHtml(String texto) {
		if (texto == null)
			return null;
		
		String tmp = texto;
		
		//Ser� el texto desde el primer '>' y desde ah� al primer '<'
		int pos = tmp.indexOf('>');
		if ( pos > -1) {
			tmp = tmp.substring(pos + 1);
			pos = tmp.indexOf('<');
			if (pos > -1) {
				tmp = tmp.substring(0, pos);
				return tmp.trim();
			} else
				return texto;
		} else 
			return texto;
				
	}
	
}
