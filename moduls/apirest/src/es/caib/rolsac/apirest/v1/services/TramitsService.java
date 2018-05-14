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

import org.ibit.rol.sac.model.Tramite;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.Tramits;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroTramits;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaTramits;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_TRAMITE ) 
@Api( value = "/"+ Constantes.ENTIDAD_TRAMITE,   tags = Constantes.ENTIDAD_TRAMITE  )
public class TramitsService {
		
		
	/**
	 * Listado de Tramites.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista los tramites",
	    notes = "Lista los tramites disponibles en funcion de los filtros"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaTramits.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaTramits llistar(
		@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,		
		@ApiParam( value = "Filtro de Paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion,
		@ApiParam( value = "Filtro de tramites: " + FiltroTramits.SAMPLE) @FormParam("filtro") FiltroTramits filtro//,
		//@ApiParam( value = "Filtro de Orden: " + Orden.SAMPLE_ORDEN_PROCEDIMIENTO) @FormParam("orden") Orden orden						
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {
						
		if(filtro==null) {
			filtro = new FiltroTramits(); 
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
		/*
		// si viene el orden intentamos rellenarlo
		if(orden!=null) {
			List<CampoOrden> ord = orden.getListaOrden();
			if(ord!=null && ord.size()>0) {
				for (CampoOrden campoOrden : ord) {
					if(campoOrden.getCampo().equals(Orden.CAMPO_ORD_PROCEDIMIENTO_CODIGO)||
							campoOrden.getCampo().equals(Orden.CAMPO_ORD_PROCEDIMIENTO_FECHA_ACTUALIZACION)||
							campoOrden.getCampo().equals(Orden.CAMPO_ORD_PROCEDIMIENTO_FECHA_PUBLICACION)) {
						fg.addOrden(campoOrden.getCampo(), campoOrden.getTipoOrden());	
					}
				}
			}		
		}*/
						
		return getRespuesta(fg);
	}
	
	
	/**
	 * Para obtener un tramite.
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
	    value = "Obtiene un tramite",
	    notes = "Obtiene el tramite con el código indicado"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaTramits.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaTramits  getPorId(  
			@ApiParam( value = "Codigo tramite", required = true ) @PathParam( "codigo") final  String codigo,
		    @ApiParam( value = "codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang
			) throws Exception,ValidationException {

		FiltroGenerico fg = new FiltroGenerico();
		
		if(lang!=null) {
			fg.setLang(lang);	
		}
		fg.setId(new Long(codigo));		
		
		return getRespuesta(fg);			
	}
	
	
    private RespuestaTramits getRespuesta(FiltroGenerico filtro) throws DelegateException {		
    	es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getTramiteDelegate().consultaTramites(filtro);	
		List <Tramits> lista = new ArrayList <Tramits>();
			
		for (Tramite nodo : Utiles.castList(Tramite.class, resultadoBusqueda.getListaResultados())) {
			Tramits elemento = new Tramits(nodo,null,filtro.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaTramits r = new RespuestaTramits(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}
	
	

}
