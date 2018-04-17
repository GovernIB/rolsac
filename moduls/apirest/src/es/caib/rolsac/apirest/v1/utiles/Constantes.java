package es.caib.rolsac.apirest.v1.utiles;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Clase de constantes. 
 * 
 * @author slromero
 *
 */
public class Constantes {
	
	private static Log log = LogFactory.getLog(Constantes.class);
	
	public static final String SALTO_LINEA="<br>";

	/** Version del api (minusculas, sin espacios)**/
	public static final String API_VERSION = "v1";
	
	/** Idioma por defecto. solo para Swagger **/
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
	
	
	
	public static final String TXT_DEFINICION_CLASE = "Definición de la clase ";
	public static final String TXT_RESPUESTA = "Respuesta ";
	
	public static final String URL_MODULO = "/rolsac/api/rest/";
	public static final String URL_BASE = Constantes.getUrlPropiedades()+ URL_MODULO + Constantes.API_VERSION+"/";
	
	public static final String ENTIDAD_IDIOMA = "idiomas";
	public static final String URL_IDIOMA = ENTIDAD_IDIOMA+"/{0}";
		
	public static final String ENTIDAD_UA = "unidades_administrativas";
	public static final String URL_UA = ENTIDAD_UA+"/{0}";

	public static final String ENTIDAD_ESPACIO_TERRITORIAL = "espacios_territoriales";
	public static final String URL_ESPACIO_TERRITORIAL = ENTIDAD_ESPACIO_TERRITORIAL+"/{0}";

	public static final String ENTIDAD_ARCHIVO = "archivos";
	public static final String URL_ARCHIVO = ENTIDAD_ARCHIVO + "/{0}";

	public static final String ENTIDAD_PUBLICO = "publicos_objetivo";
	public static final String URL_PUBLICO = ENTIDAD_PUBLICO + "/{0}";
	
	public static final String ENTIDAD_ARUPACIO_FET_VITAL = "agrupaciones_hechos_vitales";
	public static final String URL_ARUPACIO_FET_VITAL = ENTIDAD_ARUPACIO_FET_VITAL + "/{0}";
	
	public static final String ENTIDAD_ARUPACIO_MATERIES = "agrupaciones_materias";
	public static final String URL_ARUPACIO_MATERIES = ENTIDAD_ARUPACIO_MATERIES + "/{0}";
	
	public static final String ENTIDAD_SECCION = "secciones";
	public static final String URL_SECCION = ENTIDAD_SECCION + "/{0}";
	
	

	
	
	
	
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
			res = System.getProperty("es.caib.rolsac.api.rest.urlbase");
			if(org.apache.commons.lang.StringUtils.isEmpty(res)) {
				res = org.apache.commons.lang.StringUtils.isEmpty(System.getProperty("es.caib.rolsac.portal.url"))?"":System.getProperty("es.caib.rolsac.portal.url");
			}
		} catch (Exception e) {
			log.error("Error al recuperar las propiedades de URL "+e.getMessage());
			res=e.getMessage();
		}
		return res;
	}
		 

}






