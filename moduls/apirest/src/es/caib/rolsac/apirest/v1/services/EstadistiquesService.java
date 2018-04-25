package es.caib.rolsac.apirest.v1.services;

import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_ESTADISTICAS ) 
@Api( value = "/"+ Constantes.ENTIDAD_ESTADISTICAS,   tags = Constantes.ENTIDAD_ESTADISTICAS  )
public class EstadistiquesService {
		
	
	
	/**
	 * Actualización de estadísticas de UA
	 * @param codigo
	 * @return
	 * @throws Exception
	 * @throws ValidationException
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("grabar_acceso_ua/{codigo}")
	@ApiOperation( 
	    value = "Actualiza las estadisticas de una unidad adminsitrativa",
	    notes = "Actualiza las estadisticas de una unidad adminsitrativa"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaError  actualizaUA(  
			@ApiParam( value = "Código unidad administrativa", required = true ) @PathParam( "codigo") final  Long codigo
			) throws Exception,ValidationException {
		
		 DelegateUtil.getEstadisticaDelegate().grabarEstadisticaUnidadAdministrativa(codigo);
		 return new RespuestaError("200",Constantes.MSJ_200_GENERICO);
	}
	
	
	
	/**
	 * Actualización de estadísticas de ficha
	 * @param codigo
	 * @return
	 * @throws Exception
	 * @throws ValidationException
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("grabar_acceso_ficha/{codigo}")
	@ApiOperation( 
	    value = "Actualiza las estadisticas de una ficha",
	    notes = "Actualiza las estadisticas de una ficha"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaError  actualizaFicha(  
			@ApiParam( value = "Código ficha", required = true ) @PathParam( "codigo") final  Long codigo
			) throws Exception,ValidationException {
		
		 DelegateUtil.getEstadisticaDelegate().grabarEstadisticaFicha(codigo);
		 return new RespuestaError("200",Constantes.MSJ_200_GENERICO);
	}
	
	
	/**
	 * Actualización de estadísticas de normativa
	 * @param codigo
	 * @return
	 * @throws Exception
	 * @throws ValidationException
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("grabar_acceso_normativa/{codigo}")
	@ApiOperation( 
	    value = "Actualiza las estadisticas de una normativa",
	    notes = "Actualiza las estadisticas de una normativa"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaError  actualizaNormativa(  
			@ApiParam( value = "Código normativa", required = true ) @PathParam( "codigo") final  Long codigo
			) throws Exception,ValidationException {
		
		 DelegateUtil.getEstadisticaDelegate().grabarEstadisticaNormativa(codigo);
		 return new RespuestaError("200",Constantes.MSJ_200_GENERICO);
	}
	
	/**
	 * Actualización de estadísticas de procedimiento
	 * @param codigo
	 * @return
	 * @throws Exception
	 * @throws ValidationException
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Path("grabar_acceso_procedimiento/{codigo}")
	@ApiOperation( 
	    value = "Actualiza las estadisticas de un procedimiento",
	    notes = "Actualiza las estadisticas de un procedimiento"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaError  actualizaProcedimiento(  
			@ApiParam( value = "Código procedimiento", required = true ) @PathParam( "codigo") final  Long codigo
			) throws Exception,ValidationException {
		
		 DelegateUtil.getEstadisticaDelegate().grabarEstadisticaProcedimiento(codigo);
		 return new RespuestaError("200",Constantes.MSJ_200_GENERICO);
	}

}
