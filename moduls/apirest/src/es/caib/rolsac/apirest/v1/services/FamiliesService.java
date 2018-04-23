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

import org.ibit.rol.sac.model.Familia;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.Families;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFamilies;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_FAMILIA ) 
@Api( value = "/"+ Constantes.ENTIDAD_FAMILIA,   tags = Constantes.ENTIDAD_FAMILIA  )
public class FamiliesService {
		
		
	/**
	 * Listado de Familias.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista de familias",
	    notes = "Lista todas las familias disponibles"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaFamilies.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaFamilies llistarFamilies(
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
	 * Para obtener la familia.
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
	    value = "Obtiene una familia",
	    notes = "Obtiene la familia con el id(código) indicado"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaFamilies.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaFamilies  getFamilia(  
			@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,
			@ApiParam( value = "Código de familia", required = true ) @PathParam( "codigo") final  String codigo
			) throws Exception,ValidationException {
		
		FiltroGenerico fg = new FiltroGenerico();
		
		if(lang!=null) {
			fg.setLang(lang);	
		}
		
		fg.setId(new Long(codigo));			
		return getRespuesta(fg);
	}
	
	
	 private RespuestaFamilies getRespuesta(FiltroGenerico filtro) throws DelegateException {		
		 es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getFamiliaDelegate().consultaFamilias(filtro);
		
		List <Families> lista = new ArrayList <Families>();
			
		for (Familia nodo : Utiles.castList(Familia.class, resultadoBusqueda.getListaResultados())) {
			Families elemento = new Families(nodo,null,filtro.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaFamilies r = new RespuestaFamilies(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}		

}
