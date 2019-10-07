package es.caib.rolsac.apirest.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ibit.rol.sac.model.Servicio;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.Serveis;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroServeis;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaEnllacos;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaServeis;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/" + Constantes.ENTIDAD_SERVICIOS)
@Api(value = "/" + Constantes.ENTIDAD_SERVICIOS, tags = Constantes.ENTIDAD_SERVICIOS)
public class ServeisService {

	/**
	 * Para obtener el enlace.
	 *
	 * @param idioma
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Produces({ MediaType.APPLICATION_JSON })
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/enlaceTelematico/{codigo}")
	@ApiOperation(value = "Obtiene un enlace telematico", notes = "Obtiene el enlace con el id (código) indicado y su idioma")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = Constantes.MSJ_200_GENERICO, response = RespuestaEnllacos.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class) })

	public RespuestaServeis getEnlaceTelematico(
			@ApiParam(value = "Código enlace", required = true) @PathParam("codigo") final String codigo,
			@ApiParam(value = "Código de idioma", required = false) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang)
			throws Exception, ValidationException {

		final String url = DelegateUtil.getServicioDelegate().getEnlaceTelematico(Long.valueOf(codigo), lang);

		final RespuestaServeis respuesta = new RespuestaServeis();
		respuesta.setUrl(url);
		return respuesta;
	}

	/**
	 * Listado de servicios.
	 *
	 * @return
	 * @throws DelegateException
	 */
	@Produces({ MediaType.APPLICATION_JSON })
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation(value = "Lista los servicios", notes = "Lista los servicios disponibles en funcion de los filtros")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = Constantes.MSJ_200_GENERICO, response = RespuestaServeis.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class) })

	public RespuestaServeis llistar(
			@ApiParam(value = "Codigo de idioma", required = false) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,
			@ApiParam(value = "Filtro de Paginación: "
					+ FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") final FiltroPaginacion filtroPaginacion,
			@ApiParam(value = "Filtro de servicios: " + FiltroServeis.SAMPLE) @FormParam("filtro") FiltroServeis filtro// ,
	// @ApiParam( value = "Filtro de Orden: " + Orden.SAMPLE_ORDEN_PROCEDIMIENTO)
	// @FormParam("orden") Orden orden
	) throws DelegateException, ExcepcionAplicacion, ValidationException {

		if (filtro == null) {
			filtro = new FiltroServeis();
		}
		/*
		 * if(filtro.getCodigoUADir3()!=null && filtro.getCodigoUA()!=null) { throw new
		 * ExcepcionAplicacion(
		 * 400,"No se puede indicar un codigoUA y un codigoUADir3 simultaneamente"); }
		 */
		final FiltroGenerico fg = filtro.toFiltroGenerico();

		if (lang != null) {
			fg.setLang(lang);
		}

		// si no vienen los filtros se completan con los datos por defecto
		if (filtroPaginacion != null) {
			fg.setPageSize(filtroPaginacion.getSize());
			fg.setPage(filtroPaginacion.getPage());
		}

		return getRespuesta(fg);
	}

	/**
	 * Para obtener un servicio.
	 *
	 * @param idioma
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Produces({ MediaType.APPLICATION_JSON })
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/{codigo}")
	@ApiOperation(value = "Obtiene un servicio", notes = "Obtiene el servicio con el código indicado")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = Constantes.MSJ_200_GENERICO, response = RespuestaServeis.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class) })

	public RespuestaServeis getPorId(
			@ApiParam(value = "Codigo servicio", required = true) @PathParam("codigo") final String codigo,
			@ApiParam(value = "codigo de idioma", required = false) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang)
			throws Exception, ValidationException {

		final FiltroGenerico fg = new FiltroGenerico();

		if (lang != null) {
			fg.setLang(lang);
		}
		fg.setId(new Long(codigo));

		return getRespuesta(fg);
	}

	private RespuestaServeis getRespuesta(final FiltroGenerico filtro) throws DelegateException {
		final es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getServicioDelegate()
				.consultaServicios(filtro);
		final List<Serveis> lista = new ArrayList<Serveis>();

		for (final Servicio nodo : Utiles.castList(Servicio.class, resultadoBusqueda.getListaResultados())) {
			final Serveis elemento = new Serveis(nodo, null, filtro.getLang(), true);
			lista.add(elemento);
		}

		final RespuestaServeis r = new RespuestaServeis(Response.Status.OK.getStatusCode() + "",
				Constantes.mensaje200(lista.size()), new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}

}
