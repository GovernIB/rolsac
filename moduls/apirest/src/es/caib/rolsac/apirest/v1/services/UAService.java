package es.caib.rolsac.apirest.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.UnitatAdministrativa;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroUA;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaUA;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
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
	@Consumes("application/json, multipart/form-data")
	@Path("/")
	@ApiOperation( 
	    value = "unitats_administratives",
	    notes = "Lista todos las Unidades administrativas disponibles"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaUA.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	public RespuestaUA llistarUA() throws DelegateException,ExcepcionAplicacion,ValidationException  {
		final List<UnitatAdministrativa> UAs = new ArrayList<UnitatAdministrativa>();
		FiltroUA fua = new FiltroUA();
		fua.setCodigoUAPadre(1);
		
		
		final List<org.ibit.rol.sac.model.UnidadAdministrativa> rUAs = DelegateUtil.getUADelegate().consultaUnidadesAdministrativas(fua);
		for(org.ibit.rol.sac.model.UnidadAdministrativa rUA : rUAs) {
			UnitatAdministrativa ua = new UnitatAdministrativa(rUA,null,"ca",true);
			UAs.add(ua);
		}				
		RespuestaUA r = new RespuestaUA(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(UAs.size()) , new Integer(UAs.size()), UAs);
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
