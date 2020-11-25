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

	public static final String SALTO_LINEA = "<br>";

	/** Version del api (minusculas, sin espacios) **/
	public static final String API_VERSION = "v1";

	/** Idioma por defecto. solo para Swagger **/
	public static final String IDIOMA_DEFECTO = "ca";

	// Mensajes

	// 200
	public static final String MSJ_200_GENERICO = "La petición ha sido procesada correctamente";
	public static final String MSJ_200_SIN_RESULTADOS = "No se han localizado resultados para la busqueda";

	// 400;
	public static final String MSJ_400_GENERICO = "La petición recibida es incorrecta";

	// 404;
	public static final String MSJ_404_GENERICO = "El recurso al que intenta acceder no existe";

	// 500;
	public static final String MSJ_500_GENERICO = "Ocurrio un error inesperado al procesar la petición";

	public static final String TXT_DEFINICION_CLASE = "Definición de la clase ";
	public static final String TXT_RESPUESTA = "Respuesta ";

	public static final String URL_MODULO = "/rolsac/api/rest/";
	public static final String URL_BASE = Constantes.getUrlPropiedades() + URL_MODULO + Constantes.API_VERSION + "/";

	public static final String ENTIDAD_IDIOMA = "idiomas";
	public static final String URL_IDIOMA = ENTIDAD_IDIOMA + "/{0}";

	public static final String ENTIDAD_PLATAFORMA = "plataformas";
	public static final String URL_PLATAFORMA = ENTIDAD_PLATAFORMA + "/{0}";

	public static final String ENTIDAD_UA = "unidades_administrativas";
	public static final String URL_UA = ENTIDAD_UA + "/{0}";

	public static final String ENTIDAD_ESPACIO_TERRITORIAL = "espacios_territoriales";
	public static final String URL_ESPACIO_TERRITORIAL = ENTIDAD_ESPACIO_TERRITORIAL + "/{0}";

	public static final String ENTIDAD_ARCHIVO = "archivos";
	public static final String URL_ARCHIVO = ENTIDAD_ARCHIVO + "/{0}";

	public static final String ENTIDAD_PUBLICO = "publicos_objetivo";
	public static final String URL_PUBLICO = ENTIDAD_PUBLICO + "/{0}";

	public static final String ENTIDAD_AGRUPACIO_FET_VITAL = "agrupaciones_hechos_vitales";
	public static final String URL_AGRUPACIO_FET_VITAL = ENTIDAD_AGRUPACIO_FET_VITAL + "/{0}";

	public static final String ENTIDAD_AGRUPACIO_MATERIES = "agrupaciones_materias";
	public static final String URL_AGRUPACIO_MATERIES = ENTIDAD_AGRUPACIO_MATERIES + "/{0}";

	public static final String ENTIDAD_SECCION = "secciones";
	public static final String URL_SECCION = ENTIDAD_SECCION + "/{0}";

	public static final String ENTIDAD_BOLETINES = "boletines";
	public static final String URL_BOLETINES = ENTIDAD_BOLETINES + "/{0}";

	public static final String ENTIDAD_CATALOGO_DOCUMENTOS = "catalogo_documentos";
	public static final String URL_CATALOGO_DOCUMENTOS = ENTIDAD_CATALOGO_DOCUMENTOS + "/{0}";

	public static final String ENTIDAD_DOCUMENTOS = "documentos";
	public static final String URL_DOCUMENTOS = ENTIDAD_DOCUMENTOS + "/{0}";

	public static final String ENTIDAD_DOCUMENTOS_TRAMITE = "documentos_tramites";
	public static final String URL_DOCUMENTOS_TRAMITES = ENTIDAD_DOCUMENTOS_TRAMITE + "/{0}";

	public static final String ENTIDAD_DOCUMENTOS_NORMATIVAS = "documentos_normativas";
	public static final String URL_DOCUMENTOS_NORMATIVAS = ENTIDAD_DOCUMENTOS_NORMATIVAS + "/{0}";

	public static final String ENTIDAD_PROCEDIMIENTO = "procedimientos";
	public static final String URL_PROCEDIMIENTO = ENTIDAD_PROCEDIMIENTO + "/{0}";

	public static final String ENTIDAD_FICHA = "fichas";
	public static final String URL_FICHA = ENTIDAD_FICHA + "/{0}";

	public static final String ENTIDAD_FICHAUA = "fichas_ua";
	public static final String URL_FICHAUA = ENTIDAD_FICHAUA + "/{0}";

	public static final String ENTIDAD_TRAMITE = "tramites";
	public static final String URL_TRAMITE = ENTIDAD_TRAMITE + "/{0}";

	public static final String ENTIDAD_NORMATIVAS = "normativas";
	public static final String URL_NORMATIVAS = ENTIDAD_NORMATIVAS + "/{0}";

	public static final String ENTIDAD_FAMILIA = "familias";
	public static final String URL_FAMILIA = ENTIDAD_FAMILIA + "/{0}";

	public static final String ENTIDAD_FORMULARIO = "formularios";
	public static final String URL_FORMULARIO = ENTIDAD_FORMULARIO + "/{0}";

	public static final String ENTIDAD_ICONO_FAMILIA = "iconos_familias";
	public static final String URL_ICONO_FAMILIA = ENTIDAD_ICONO_FAMILIA + "/{0}";

	public static final String ENTIDAD_MATERIA_AGRUPACION = "materias_agrupaciones";
	public static final String URL_MATERIA_AGRUPACION = ENTIDAD_MATERIA_AGRUPACION + "/{0}";

	public static final String ENTIDAD_PERFIL = "perfiles";
	public static final String URL_PERFIL = ENTIDAD_PERFIL + "/{0}";

	public static final String ENTIDAD_TIPO = "tipos";
	public static final String URL_TIPO = ENTIDAD_TIPO + "/{0}";

	public static final String ENTIDAD_TIPO_AFECTACION = "tipos_afectacion";
	public static final String URL_TIPO_AFECTACION = ENTIDAD_TIPO_AFECTACION + "/{0}";

	public static final String ENTIDAD_UNIDAD_MATERIA = "unidades_materias";
	public static final String URL_UNIDAD_MATERIA = ENTIDAD_UNIDAD_MATERIA + "/{0}";

	public static final String ENTIDAD_MATERIA = "materias";
	public static final String URL_MATERIA = ENTIDAD_MATERIA + "/{0}";

	public static final String ENTIDAD_EDIFICIO = "edificios";
	public static final String URL_EDIFICIO = ENTIDAD_EDIFICIO + "/{0}";

	public static final String ENTIDAD_ENLACE = "enlaces";
	public static final String URL_ENLACE = ENTIDAD_ENLACE + "/{0}";

	public static final String ENTIDAD_ENLACE_TELEMATICO = "enlaceTelematico";
	public static final String URL_ENLACE_TELEMATICO = ENTIDAD_ENLACE_TELEMATICO + "/{0}";

	public static final String ENTIDAD_HECHOS_VITALES = "hechos_vitales";
	public static final String URL_HECHOS_VITALES = ENTIDAD_HECHOS_VITALES + "/{0}";

	public static final String ENTIDAD_PERSONAL = "personal";
	public static final String URL_PERSONAL = ENTIDAD_PERSONAL + "/{0}";

	public static final String ENTIDAD_USUARIOS = "usuarios";
	public static final String URL_USUARIOS = ENTIDAD_USUARIOS + "/{0}";

	public static final String ENTIDAD_SERVICIOS = "servicios";
	public static final String URL_SERVICIOS = ENTIDAD_SERVICIOS + "/{0}";

	public static final String ENTIDAD_ESTADISTICAS = "estadisticas";
	public static final String ENTIDAD_TASA = "tasas";

	public static final String ENTIDAD_PLATAFORMAS = "plataformas";
	public static final String URL_PLATAFORMAS = ENTIDAD_PLATAFORMAS + "/{0}";

	public static String mensaje200(final int numeroElementos) {
		String res = null;
		if (numeroElementos == 0) {
			res = MSJ_200_SIN_RESULTADOS;
		}
		return res;
	}

	public static final String getUrlPropiedades() {
		String res = "";
		try {
			res = System.getProperty("es.caib.rolsac.api.rest.urlbase");
			if (org.apache.commons.lang.StringUtils.isEmpty(res)) {
				res = org.apache.commons.lang.StringUtils.isEmpty(System.getProperty("es.caib.rolsac.portal.url")) ? ""
						: System.getProperty("es.caib.rolsac.portal.url");
			}
		} catch (final Exception e) {
			log.error("Error al recuperar las propiedades de URL " + e.getMessage());
			res = e.getMessage();
		}
		return res;
	}

	public static void main(final String args[]) {
		System.out.println("XC");
	}
}
