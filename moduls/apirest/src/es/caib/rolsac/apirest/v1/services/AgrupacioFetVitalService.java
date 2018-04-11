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

import org.ibit.rol.sac.model.AgrupacionHechoVital;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.AgrupacioFetVital;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroAgrupacioFetVital;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaAgrupacioFetVital;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_ARUPACIO_FET_VITAL ) 
@Api( value = "/"+ Constantes.ENTIDAD_ARUPACIO_FET_VITAL,   tags = Constantes.ENTIDAD_ARUPACIO_FET_VITAL  )
public class AgrupacioFetVitalService {
		
		
	/**
	 * Listado de Agrupacions fets vitals.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista de agrupaciones de hechos vitales",
	    notes = "Lista todas las Agrupaciones de hechos vitales disponibles"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaAgrupacioFetVital.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaAgrupacioFetVital llistarAFV(
		@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("codigoIdioma") final String codigoIdioma,		
		@ApiParam( value = "Filtro de Paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion,
		@ApiParam( value = "Filtro de Agrupación de hechos vitales: " + FiltroAgrupacioFetVital.SAMPLE) @FormParam("filtro") FiltroAgrupacioFetVital filtro				
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {
		
				
		if(filtro==null) {
			filtro = new FiltroAgrupacioFetVital(); 
		}
		FiltroGenerico fg = filtro.toFiltroGenerico();
		
		if(codigoIdioma!=null) {
			fg.setLang(codigoIdioma);	
		}
				
		//si no vienen los filtros se completan con los datos por defecto (los definidos dentro de Filtro generico)
		if(filtroPaginacion!=null) {
			fg.setPageSize(filtroPaginacion.getSize());
			fg.setPage(filtroPaginacion.getPage());
		}
		
				
		es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getAgrupacionHVDelegate().consultaAgrupacionsFetsVitals(fg);
		
		List <AgrupacioFetVital> lista = new ArrayList <AgrupacioFetVital>();
			
		for (AgrupacionHechoVital nodo : Utiles.castList(AgrupacionHechoVital.class, resultadoBusqueda.getListaResultados())) {
			AgrupacioFetVital elemento = new AgrupacioFetVital(nodo,null,fg.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaAgrupacioFetVital r = new RespuestaAgrupacioFetVital(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}
	
	
	/**
	 * Para obtener el idioma.
	 * @param idioma 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/{codigoAFV}")
	@ApiOperation( 
	    value = "Obtiene una Agrupacion de hecho vital",
	    notes = "Obtiene La Agrupacion de hecho vital con el id(código) indicado"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaAgrupacioFetVital.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaAgrupacioFetVital  getAFV(  
			@ApiParam( value = "Codigo Agrupacion de hecho vital", required = true ) @PathParam( "codigoAFV") final  String codigoAFV,
		    @ApiParam( value = "codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("codigoIdioma") final String codigoIdioma
			) throws Exception,ValidationException {
		
		FiltroGenerico fg = new FiltroGenerico();
		if(codigoIdioma!=null) {
			fg.setLang(codigoIdioma);	
		}
		
		fg.setId(new Long(codigoAFV));	
		
		es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getAgrupacionHVDelegate().consultaAgrupacionsFetsVitals(fg);
		
		List <AgrupacioFetVital> lista = new ArrayList <AgrupacioFetVital>();
			
		for (AgrupacionHechoVital nodo : Utiles.castList(AgrupacionHechoVital.class, resultadoBusqueda.getListaResultados())) {
			AgrupacioFetVital elemento = new AgrupacioFetVital(nodo,null,fg.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaAgrupacioFetVital r = new RespuestaAgrupacioFetVital(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
		
		
		
	}

}
