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

import org.ibit.rol.sac.model.Normativa;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.Normatives;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroNormatives;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaNormatives;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_NORMATIVAS ) 
@Api( value = "/"+ Constantes.ENTIDAD_NORMATIVAS,   tags = Constantes.ENTIDAD_NORMATIVAS  )
public class NormativesService {
		
		
	/**
	 * Listado de normativas.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista las normativas",
	    notes = "Lista las normativas disponibles en funcion de los filtros"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaNormatives.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaNormatives llistar(
		@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,		
		@ApiParam( value = "Filtro de Paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion,
		@ApiParam( value = "Filtro de normativas: " + FiltroNormatives.SAMPLE) @FormParam("filtro") FiltroNormatives filtro//,
		//@ApiParam( value = "Filtro de Orden: " + Orden.SAMPLE_ORDEN_FICHAUA) @FormParam("orden") Orden orden						
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {
						
		if(filtro==null) {
			filtro = new FiltroNormatives(); 
		}
		FiltroGenerico fg = filtro.toFiltroGenerico();
		
		if(lang!=null) {
			fg.setLang(lang);	
		}
				
		//si no vienen los filtros se completan con los datos por defecto
		if(filtroPaginacion!=null) {
			fg.setPageSize(filtroPaginacion.getSize());
			fg.setPage(filtroPaginacion.getPage());
		}
						
		return getRespuesta(fg);
	}
	
	
	/**
	 * Para obtener una normativa.
	 * @param idioma 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/{codigo}")
	@ApiOperation( 
	    value = "Obtiene una normativa",
	    notes = "Obtiene La normativa con el código indicado"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaNormatives.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaNormatives  getPorId(  
			@ApiParam( value = "Codigo normativa", required = true ) @PathParam( "codigo") final  String codigo,
		    @ApiParam( value = "codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang
			) throws Exception,ValidationException {

		FiltroGenerico fg = new FiltroGenerico();
		
		if(lang!=null) {
			fg.setLang(lang);	
		}
		fg.setId(new Long(codigo));		
		
		return getRespuesta(fg);	
		
	}
	
	
    private RespuestaNormatives getRespuesta(FiltroGenerico filtro) throws DelegateException {		
    	es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getNormativaDelegate().consultaNormativas(filtro);	
		List <Normatives> lista = new ArrayList <Normatives>();
			
		for (Normativa nodo : Utiles.castList(Normativa.class, resultadoBusqueda.getListaResultados())) {
			Normatives elemento = new Normatives(nodo,null,filtro.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaNormatives r = new RespuestaNormatives(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}
	
	

}
