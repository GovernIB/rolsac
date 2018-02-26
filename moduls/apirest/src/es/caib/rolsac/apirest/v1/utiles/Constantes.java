package es.caib.rolsac.apirest.v1.utiles;

/**
 * Clase de constantes. 
 * 
 * @author slromero
 *
 */
public class Constantes {

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
	
	
	

	
}
