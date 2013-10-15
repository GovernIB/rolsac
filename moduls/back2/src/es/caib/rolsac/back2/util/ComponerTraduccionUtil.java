package es.caib.rolsac.back2.util;

import org.apache.commons.lang.StringEscapeUtils;

public class ComponerTraduccionUtil
{
	/**
	 * Este método resuelve un fallo en las traducciones, parece que en las trablas
	 * de traducciones hay puesto a piñon los tags "<p>" y "</p>
	 * 
	 * @param inPut testo que se quiere escapar
	 * @return String compuesto preparado para poder traducirlo correctamente
	 */
	public static String montarTranslate(String inPut)
	{
		StringBuilder encode = new StringBuilder("<p>");
		encode.append(StringEscapeUtils.escapeXml(inPut));
		encode.append("</p>");
		return encode.toString();
	}
	
	/**
	 * Este método desmonta los tags añadidos por el anterior
	 * @param inPut
	 * @return
	 */
	public static String desmontarTranslate(String inPut)
	{
		return inPut.subSequence(3, inPut.length()-4).toString();
	}

}
