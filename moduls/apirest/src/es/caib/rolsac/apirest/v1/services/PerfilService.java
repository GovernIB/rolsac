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

import org.ibit.rol.sac.model.PerfilCiudadano;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.Perfil;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaPerfil;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_PERFIL ) 
@Api( value = "/"+ Constantes.ENTIDAD_PERFIL,   tags = Constantes.ENTIDAD_PERFIL  )
public class PerfilService {
		
		
	/**
	 * Listado de Perfiles
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista de perfiles",
	    notes = "Lista todos los perfiles disponibles"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaPerfil.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaPerfil llistarPerfils(	
		@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,
		@ApiParam( value = "Filtro de paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion				
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {

		FiltroGenerico fg = new FiltroGenerico();
		
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
	 * Para obtener el perfil.
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
	    value = "Obtiene un perfil",
	    notes = "Obtiene el perfil con el id(código) indicado"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaPerfil.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaPerfil  getMateriaAgrupacion(  
			@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,
			@ApiParam( value = "Código de perfil", required = true ) @PathParam( "codigo") final  String codigo
			) throws Exception,ValidationException {
		
		FiltroGenerico fg = new FiltroGenerico();
		
		if(lang!=null) {
			fg.setLang(lang);	
		}
		
		fg.setId(new Long(codigo));			
		return getRespuesta(fg);
	}
	
	
	 private RespuestaPerfil getRespuesta(FiltroGenerico fg) throws DelegateException {		
		 es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getPerfilDelegate().consultaPerfil(fg);
		
		List <Perfil> lista = new ArrayList <Perfil>();
			
		for (PerfilCiudadano nodo : Utiles.castList(PerfilCiudadano.class, resultadoBusqueda.getListaResultados())) {
			Perfil elemento = new Perfil(nodo,null,fg.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaPerfil r = new RespuestaPerfil(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}		

}