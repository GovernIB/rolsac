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

import org.ibit.rol.sac.model.TramitePlantilla;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.Plantillas;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPlantillas;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaPlantillas;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/" + Constantes.ENTIDAD_PLANTILLAS)
@Api(value = "/" + Constantes.ENTIDAD_PLANTILLAS, tags = Constantes.ENTIDAD_PLANTILLAS)
public class PlantillasService {

	/**
	 * Listado de plantillas.
	 *
	 * @return
	 * @throws DelegateException
	 */
	@Produces({ MediaType.APPLICATION_JSON })
	@POST
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation(value = "Lista los plantillas", notes = "Lista los plantillas disponibles en funcion de los filtros")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = Constantes.MSJ_200_GENERICO, response = RespuestaPlantillas.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class) })

	public RespuestaPlantillas llistar(
			@ApiParam(value = "Codigo de idioma", required = false) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,
			@ApiParam(value = "Filtro de Paginación: "
					+ FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") final FiltroPaginacion filtroPaginacion,
			@ApiParam(value = "Filtro de plantillas: "
					+ FiltroPlantillas.SAMPLE) @FormParam("filtro") FiltroPlantillas filtro// ,
	// @ApiParam( value = "Filtro de Orden: " + Orden.SAMPLE_ORDEN_PROCEDIMIENTO)
	// @FormParam("orden") Orden orden
	) throws DelegateException, ExcepcionAplicacion, ValidationException {

		if (filtro == null) {
			filtro = new FiltroPlantillas();
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
			@ApiResponse(code = 200, message = Constantes.MSJ_200_GENERICO, response = RespuestaPlantillas.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class) })

	public RespuestaPlantillas getPorId(
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

	private RespuestaPlantillas getRespuesta(final FiltroGenerico filtro) throws DelegateException {
		final es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getTramitePlantillaDelegate()
				.consultaPlantillas(filtro);
		final List<Plantillas> lista = new ArrayList<Plantillas>();

		for (final TramitePlantilla nodo : Utiles.castList(TramitePlantilla.class,
				resultadoBusqueda.getListaResultados())) {
			final Plantillas elemento = new Plantillas(nodo, null, filtro.getLang(), true);
			lista.add(elemento);
		}

		final RespuestaPlantillas r = new RespuestaPlantillas(Response.Status.OK.getStatusCode() + "",
				Constantes.mensaje200(lista.size()), new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}

}
