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

import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.FetsVitals;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroFetsVitals;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFetsVitals;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_HECHOS_VITALES ) 
@Api( value = "/"+ Constantes.ENTIDAD_HECHOS_VITALES,   tags = Constantes.ENTIDAD_HECHOS_VITALES  )
public class FetsVitalsService {
		
		
	/**
	 * Listado de Hechos Vitales.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista de hechos vitales",
	    notes = "Lista todos los hechos vitales"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaFetsVitals.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaFetsVitals llistar(
		@ApiParam( value = "Código de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,		
		@ApiParam( value = "Filtro de paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion,
		@ApiParam( value = "Filtro de hechos vitales: " + FiltroFetsVitals.SAMPLE) @FormParam("filtro") FiltroFetsVitals filtro
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {
		
				
		if(filtro==null) {
			filtro = new FiltroFetsVitals(); 
		}
		
		FiltroGenerico fg = filtro.toFiltroGenerico();
		
		if(lang!=null) {
			fg.setLang(lang);	
		}
				
		//si no vienen los filtros se completan con los datos por defecto (los definidos dentro de Filtro generico)
		if(filtroPaginacion!=null) {
			fg.setPageSize(filtroPaginacion.getSize());
			fg.setPage(filtroPaginacion.getPage());
		}
	
		return getRespuesta(fg);
	}
	
	
	/**
	 * Para obtener el hecho vital.
	 * @param codigo
	 * @param lang
	 * @return
	 * @throws Exception
	 * @throws ValidationException
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/{codigo}")
	@ApiOperation( 
	    value = "Obtiene un hecho vital",
	    notes = "Obtiene el hecho vital con el id(código) indicado"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaFetsVitals.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaFetsVitals  getPorId(  
			@ApiParam( value = "Código hecho vital", required = true ) @PathParam( "codigo") final  String codigo,
		    @ApiParam( value = "Código de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang
			) throws Exception,ValidationException {
		
		FiltroGenerico fg = new FiltroGenerico();
		
		if(lang!=null) {
			fg.setLang(lang);	
		}
		
		fg.setId(new Long(codigo));			
		return getRespuesta(fg);
	}
	
	
	 private RespuestaFetsVitals getRespuesta(FiltroGenerico filtro) throws DelegateException {		
		 es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getHechoVitalDelegate().consultaHechoVital(filtro);
		
		List <FetsVitals> lista = new ArrayList <FetsVitals>();
			
		for (org.ibit.rol.sac.model.HechoVital nodo : Utiles.castList(org.ibit.rol.sac.model.HechoVital.class, resultadoBusqueda.getListaResultados())) {
			FetsVitals elemento = new FetsVitals(nodo,null,filtro.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaFetsVitals r = new RespuestaFetsVitals(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}		

}
