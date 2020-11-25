package es.caib.rolsac.apirest.v1;

import java.net.URI;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.ExternalDocs;
import io.swagger.models.Info;
import io.swagger.models.Scheme;
import io.swagger.models.Swagger;
import io.swagger.models.Tag;

/**
 * Expecifica propiedades del api para swagger.
 *
 * @author Indra
 *
 */
public class BootstrapV1 extends HttpServlet {
	/** Serial version UID. */
	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(BootstrapV1.class);

	@Override
	public void init(final ServletConfig config) throws ServletException {
		final Info info = new Info().title("Rolsac REST API ")
				.description("Servicio REST API " + Constantes.API_VERSION.toUpperCase()
						+ " para la petición de datos de entidades. "
						+ "Servicio proporcionado por [http://www.caib.es](http://www.caib.es).")
				.version(Constantes.API_VERSION);

		final Swagger swagger = new Swagger().info(info);

		try {
			final String url = Constantes.getUrlPropiedades();
			if (!StringUtils.isEmpty(url)) {
				final URI uri = new URI(url);
				swagger.addScheme(uri.getScheme().equals("https") ? Scheme.HTTPS : Scheme.HTTP);
				swagger.setHost(uri.getAuthority());

			} else {
				log.error(
						"ERROR No seha podido cargar la URL del API_REST, verifique que se ha definido correctamente la propiedad de URL para el api rest");
			}
			swagger.setBasePath(Constantes.URL_MODULO + Constantes.API_VERSION);
		} catch (final Exception e) {
			e.printStackTrace();
		}

		swagger.externalDocs(new ExternalDocs("Más info contacte con el responsable.", "http://www.caib.es"));

		final String txt_descripcion = "Servicio para la obtención de información de";

		swagger.tag(new Tag().name(Constantes.ENTIDAD_AGRUPACIO_FET_VITAL)
				.description(txt_descripcion + " las agrupaciones de hechos vitales."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_AGRUPACIO_MATERIES)
				.description(txt_descripcion + " las agrupaciones de materias."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_ARCHIVO).description(txt_descripcion + " los archivos."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_BOLETINES).description(txt_descripcion + " los boletines."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_CATALOGO_DOCUMENTOS)
				.description(txt_descripcion + "l catalogo de documentos."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_DOCUMENTOS)
				.description(txt_descripcion + " los documentos de fichas y procedimientos."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_DOCUMENTOS_NORMATIVAS)
				.description(txt_descripcion + " los documentos de las normativas."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_DOCUMENTOS_TRAMITE)
				.description(txt_descripcion + " los documentos de los trámites."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_EDIFICIO).description(txt_descripcion + " los edificios."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_ENLACE).description(txt_descripcion + " los enlaces."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_ESTADISTICAS)
				.description("Servicio para la actualización de estadisticas de uso."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_ESPACIO_TERRITORIAL)
				.description(txt_descripcion + " los espacios territoriales."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_FAMILIA).description(txt_descripcion + " las familias."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_FICHA).description(txt_descripcion + " las fichas."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_FICHAUA).description(txt_descripcion + " las fichasUA."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_FORMULARIO).description(txt_descripcion + " los formularios."));
		swagger.tag(new Tag().name(Constantes.ENTIDAD_HECHOS_VITALES)
				.description(txt_descripcion + " los hechos vitales."));

		swagger.tag(
				new Tag().name(Constantes.ENTIDAD_ICONO_FAMILIA).description(txt_descripcion + " los iconos familia."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_IDIOMA).description(txt_descripcion + " los idiomas."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_MATERIA).description(txt_descripcion + " las materias."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_MATERIA_AGRUPACION)
				.description(txt_descripcion + " las materias agrupacion."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_NORMATIVAS).description(txt_descripcion + " las normativas."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_PERFIL).description(txt_descripcion + " los perfiles."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_PERSONAL).description(txt_descripcion + " el personal."));

		swagger.tag(
				new Tag().name(Constantes.ENTIDAD_PROCEDIMIENTO).description(txt_descripcion + " los procedimientos."));

		swagger.tag(
				new Tag().name(Constantes.ENTIDAD_PUBLICO).description(txt_descripcion + " los publicos objetivo."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_SECCION).description(txt_descripcion + " las secciones."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_SERVICIOS).description(txt_descripcion + " los servicios."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_TIPO_AFECTACION)
				.description(txt_descripcion + " los tipos de afectación."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_TIPO).description(txt_descripcion + " los tipos de normativas."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_TRAMITE).description(txt_descripcion + " los tramites."));

		swagger.tag(
				new Tag().name(Constantes.ENTIDAD_UA).description(txt_descripcion + " las unidades administrativas."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_UNIDAD_MATERIA)
				.description(txt_descripcion + " las unidades materias."));

		swagger.tag(new Tag().name(Constantes.ENTIDAD_USUARIOS).description(txt_descripcion + " los usuarios."));

		new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);
	}

	public static void main(final String args[]) {

		// System.out.println("EMPIEZA");
		// final Client client = Client.create();
		// final String user = "api-tib";
		// final String password = "M0n1n@s";
		// client.addFilter(new HTTPBasicAuthFilter(user, password));
		// final WebResource resource = client
		// .resource("http://caibter.indra.es/translatorib/api/services/traduccion/v1/test");
		// final Form form = new Form();
		// form.add("x", "XFOO");
		// form.add("y", "YBAR");
		//
		// System.out.println("REST - TEST");
		// final ResultadoTraduccionTexto test =
		// resource.type(MediaType.WILDCARD).accept(MediaType.WILDCARD)
		// .get(ResultadoTraduccionTexto.class);// post(ResultadoTraduccionTexto.class,
		// // form);
		// System.out.println("ERROR:" + test.isError());
		// System.out.println("DESCR:" + test.getDescripcionError());
		// System.out.println("TECTO:" + test.getTextoTraducido());
		//
		// System.out.println("REST - TEXTO");
		// final ParametrosTraduccionTexto parametros = new ParametrosTraduccionTexto();
		// parametros.setIdiomaEntrada(Idioma.CASTELLANO);
		// parametros.setIdiomaSalida(Idioma.CATALAN);
		// parametros.setTextoEntrada("Texto a traducir");
		// parametros.setTipoEntrada(TipoEntrada.TEXTO_PLANO);
		// final WebResource resource2 = client
		// .resource("http://caibter.indra.es/translatorib/api/services/traduccion/v1/texto");
		// final ResultadoTraduccionTexto resultadoTexto =
		// resource2.type(MediaType.APPLICATION_JSON)
		// .accept(MediaType.WILDCARD).post(ResultadoTraduccionTexto.class, parametros);
		// System.out.println("ERROR:" + resultadoTexto.isError());
		// System.out.println("DESCR:" + resultadoTexto.getDescripcionError());
		// System.out.println("TECTO:" + resultadoTexto.getTextoTraducido());
		//
		// System.out.println("SALE");
	}
}