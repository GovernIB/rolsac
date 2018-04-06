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

import org.ibit.rol.sac.model.UnidadAdministrativa;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.UnitatAdministrativa;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroUA;
import es.caib.rolsac.apirest.v1.model.orden.CampoOrden;
import es.caib.rolsac.apirest.v1.model.orden.Orden;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaUA;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_UA ) 
@Api( value = "/"+ Constantes.ENTIDAD_UA,   tags = "unitats_administratives"  )
public class UAService {
		
		
	/**
	 * Listado de idiomas.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "unitats_administratives",
	    notes = "Lista todos las Unidades administrativas disponibles"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaUA.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	//type, name, in, description, required
	/*@ApiImplicitParams({
	    @ApiImplicitParam(name = "codigoIdioma", value = "Codigo de idioma", dataType = "string", paramType = "form"),
	    @ApiImplicitParam(name = "filtro", value = "Filtro de Unidad Administrativa", dataType = "es.caib.rolsac.apirest.v1.model.filtros.FiltroUA", paramType = "form"),
	    @ApiImplicitParam(name = "filtroPaginacion", value = "Filtro de Paginación",  dataType = "FiltroPaginacion", paramType = "form"),
	    @ApiImplicitParam(name = "orden", value = "Filtro de Orden",  dataType = "OrdenUA", paramType = "form")
	  })*/
	
	public RespuestaUA llistarUA(
		@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("codigoIdioma") final String codigoIdioma,
		@ApiParam( value = "Filtro de Unidad Administrativa: " + FiltroUA.SAMPLE) @FormParam("filtro") FiltroUA filtro,
		@ApiParam( value = "Filtro de Paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion,
		@ApiParam( value = "Filtro de Orden: " + Orden.SAMPLE_ORDEN_UA) @FormParam("orden") Orden orden						
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {
		
				
		if(filtro==null) {
			filtro = new FiltroUA(); 
		}
		FiltroGenerico fg = filtro.toFiltroGenerico();
		
		if(codigoIdioma!=null) {
			fg.setLang(codigoIdioma);	
		}
				
		//si no vienen los filtros se completan con los datos por defecto
		if(filtroPaginacion!=null) {
			fg.setPageSize(filtroPaginacion.getSize());
			fg.setPage(filtroPaginacion.getPage());
		}
		
		// si viene el orden intentamos rellenarlo
		if(orden!=null) {
			List<CampoOrden> ord = orden.getListaOrden();
			if(ord!=null && ord.size()>0) {
				for (CampoOrden campoOrden : ord) {
					if(campoOrden.getCampo().equals(Orden.CAMPO_ORD_UA_ORDEN)) {
						fg.addOrden(campoOrden.getCampo(), campoOrden.getTipoOrden());	
					}
				}
			}		
		}
		
				
		es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getUADelegate().consultaUnidadesAdministrativas(fg);
		
		List <UnitatAdministrativa> lua = new ArrayList <UnitatAdministrativa>();
			
		for (UnidadAdministrativa rua : Utiles.castList(UnidadAdministrativa.class, resultadoBusqueda.getListaResultados())) {
			UnitatAdministrativa ua = new UnitatAdministrativa(rua,null,fg.getLang(),true);
			lua.add(ua);
		}
		
		RespuestaUA r = new RespuestaUA(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lua.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lua);
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
	@Consumes("multipart/form-data")
	@Path("/{codigoua}")
	@ApiOperation( 
	    value = "Unidad Administrativa",
	    notes = "Obtiene La Unidad Administrativa especificada"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaUA.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaUA  getUA(  
			@ApiParam( value = "Codigo Unidad Administrativa", required = true ) @PathParam( "codigoua") final  String codigoua,
		    @ApiParam( value = "codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("codigoIdioma") final String codigoIdioma
			) throws Exception,ValidationException {
		final List<UnitatAdministrativa> ruas = new ArrayList<UnitatAdministrativa>();
		final org.ibit.rol.sac.model.UnidadAdministrativa ua = DelegateUtil.getUADelegate().consultarUnidadAdministrativa(new Long(Long.parseLong(codigoua)));
		if 	(ua!=null) {
			UnitatAdministrativa rua = new UnitatAdministrativa(ua,null,codigoIdioma,true);
			ruas.add(rua);
		}		
					
		RespuestaUA r = new RespuestaUA(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(ruas.size()) , new Integer(ruas.size()), ruas);
		return r;
			
	}

}
