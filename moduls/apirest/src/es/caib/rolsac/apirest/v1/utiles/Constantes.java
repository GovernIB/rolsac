package es.caib.rolsac.apirest.v1.utiles;

/**
 * Clase de constantes. 
 * 
 * @author slromero
 *
 */
public class Constantes {
	
	public static final String SALTO_LINEA="<br>";

	/** Version del api (minusculas, sin espacios)**/
	public static final String API_VERSION = "v1";
	
	/** Idioma por defecto. **/
	public static final String IDIOMA_DEFECTO = "ca";
	
	//Mensajes 
	
	//200
	public static final String MSJ_200_GENERICO = "La petición ha sido procesada correctamente";
	public static final String MSJ_200_SIN_RESULTADOS = "No se han localizado resultados para la busqueda";
	
	//400;
	public static final String MSJ_400_GENERICO = "La petición recibida es incorrecta";
	
	//404;
	public static final String MSJ_404_GENERICO = "El recurso al que intenta acceder no existe";	
		
	//500;
	public static final String MSJ_500_GENERICO = "Ocurrio un error inesperado al procesar la petición";
	
	public static String mensaje200(int numeroElementos) {
		String res = null;
		if(numeroElementos==0) {
			res = MSJ_200_SIN_RESULTADOS;
		}
		return res;
	}
	
	public static final String getUrlPropiedades() {
		String res ="";
		try {
			res = System.getProperty("es.caib.rolsac.apirest.urlbase");
			if(org.apache.commons.lang.StringUtils.isEmpty(res)) {
				res = org.apache.commons.lang.StringUtils.isEmpty(System.getProperty("es.caib.rolsac.portal.url"))?"":System.getProperty("es.caib.rolsac.portal.url");
			}
		} catch (Exception e) {
			res=e.getMessage();
		}
		return res;
	}
		 
	public static final String URL_MODULO = "/rolsac/api/rest/";
	public static final String URL_BASE = Constantes.getUrlPropiedades()+ URL_MODULO + Constantes.API_VERSION+"/";
	
	public static final String ENTIDAD_IDIOMA = "idiomes";
	public static final String URL_IDIOMA = ENTIDAD_IDIOMA+"/{0}";
		
	public static final String ENTIDAD_UA = "unitats_administratives";
	public static final String URL_UA = ENTIDAD_UA+"/{0}";

	public static final String ENTIDAD_ESPACIO_TERRITORIAL = "espais_territorials";
	public static final String URL_ESPACIO_TERRITORIAL = ENTIDAD_ESPACIO_TERRITORIAL+"/{0}";

	public static final String ENTIDAD_ARCHIVO = "arxiu";
	public static final String URL_ARCHIVO = ENTIDAD_ARCHIVO + "/{0}";
}






