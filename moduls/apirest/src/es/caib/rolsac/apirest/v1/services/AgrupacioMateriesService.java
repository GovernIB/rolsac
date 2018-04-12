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

import org.ibit.rol.sac.model.AgrupacionMateria;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.AgrupacioMateries;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaAgrupacioMateries;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_ARUPACIO_MATERIES ) 
@Api( value = "/"+ Constantes.ENTIDAD_ARUPACIO_MATERIES,   tags = Constantes.ENTIDAD_ARUPACIO_MATERIES  )
public class AgrupacioMateriesService {
		
		
	/**
	 * Listado de Agrupacions de materies.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista de agrupaciones de materias",
	    notes = "Lista todas las Agrupaciones de materias disponibles"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaAgrupacioMateries.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaAgrupacioMateries llistarAM(
		@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("codigoIdioma") final String codigoIdioma,		
		@ApiParam( value = "Filtro de Paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion				
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {
		
		FiltroGenerico fg = new FiltroGenerico();
		
		if(codigoIdioma!=null) {
			fg.setLang(codigoIdioma);	
		}
				
		//si no vienen los filtros se completan con los datos por defecto (los definidos dentro de Filtro generico)
		if(filtroPaginacion!=null) {
			fg.setPageSize(filtroPaginacion.getSize());
			fg.setPage(filtroPaginacion.getPage());
		}
		
		return getRespuesta(fg);
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
	@Path("/{codigoAM}")
	@ApiOperation( 
	    value = "Obtiene una Agrupacion de Materias",
	    notes = "Obtiene La Agrupacion de Materias con el id(código) indicado"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaAgrupacioMateries.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaAgrupacioMateries  getAM(  
			@ApiParam( value = "Codigo Agrupacion de Materias", required = true ) @PathParam( "codigoAM") final  String codigoAM,
		    @ApiParam( value = "codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("codigoIdioma") final String codigoIdioma
			) throws Exception,ValidationException {
		
		FiltroGenerico fg = new FiltroGenerico();
		if(codigoIdioma!=null) {
			fg.setLang(codigoIdioma);	
		}
		
		fg.setId(new Long(codigoAM));			
		return getRespuesta(fg);
	}	
	
	private RespuestaAgrupacioMateries getRespuesta(FiltroGenerico fg) throws DelegateException {
		
		es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getAgrupacionMDelegate().consultaAgrupacionMaterias(fg);
		
		List <AgrupacioMateries> lista = new ArrayList <AgrupacioMateries>();
			
		for (AgrupacionMateria nodo : Utiles.castList(AgrupacionMateria.class, resultadoBusqueda.getListaResultados())) {
			AgrupacioMateries elemento = new AgrupacioMateries(nodo,null,fg.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaAgrupacioMateries r = new RespuestaAgrupacioMateries(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
				
	}

}
