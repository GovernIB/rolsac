package es.caib.rolsac.back2.util;

public class HtmlUtils
{
	/**
	 * De un string que contiene un enlace HTML extrae el t�tulo del enlace.
	 * @param texto String que contiene el enlace.
	 * @return T�tulo del enlace, si no hay enlace devuelve el texto tal cual.
	 */
	public static String obtenerTituloDeEnlaceHtml(String texto)
	{
		if (texto == null) {
			return null;
		}
		
		String tmp = texto;
		
		// Será el texto desde el primer '>' y desde ahí al primer '<'
		int pos = tmp.indexOf('>');
		if (pos > -1) {
			tmp = tmp.substring(pos + 1);
			pos = tmp.indexOf('<');
			if (pos > -1) {
				tmp = tmp.substring(0, pos);
				return tmp.trim();
			} else
				return texto;
		} else {
			return texto;
		}
	}
	
	
	/**
	 * Método para filtrar los tags html que se puedan poner en la búsqueda.
	 * Esto se debe a que tiny_mce cuando traduce y se guarda inserta dichos tags enla bbdd
	 * @param texto
	 * @return String txt
	 */
	public static String eliminarTagsHtml(String texto)
	{
		String txt = texto.replaceAll("<[^>]*>", " ");	// Escapamos los posibles tags html que se encuentran en las descripciones.
		txt = txt.replaceAll(" +", " ");				// Quitamos los espacios en blanco sobrantes del filtrado
		if (txt.equals(" ")) {
			txt = "";
		} else if (txt.startsWith(" ")) {
			txt = txt.substring(1);
		}
		return txt;
	}
	
	
	public static String html2text(String html) {
	    return html.replaceAll("\\<.*?>","");
	}
	
}
