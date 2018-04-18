package es.caib.rolsac.apirest.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ibit.rol.sac.model.Archivo;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.model.Arxius;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaArxius;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_ARCHIVO ) 
@Api( value = "/"+ Constantes.ENTIDAD_ARCHIVO,   tags = Constantes.ENTIDAD_ARCHIVO  )
public class ArxiusService {
		
	/**
	 * Obtiene el Archivo con el id especificado.
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
	    value = "Obtiene un archivo",
	    notes = "Obtiene el archivo con el id(código) indicado"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaArxius.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaArxius  getArxiu(  
			@ApiParam( value = "Código del archivo", required = true ) @PathParam( "codigo") final  String codigo
			) throws Exception,ValidationException {
		
		FiltroGenerico fg = new FiltroGenerico();
		
		fg.setId(new Long(codigo));	
		
		Archivo archivo = DelegateUtil.getArchivoDelegate().obtenerArchivo(fg.getId());		
		List <Arxius> lista = new ArrayList <Arxius>();
		if(archivo!=null) {
			Arxius elemento = new Arxius(archivo,null,fg.getLang(),true);
			lista.add(elemento);
		}
		
		return new RespuestaArxius(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(lista.size()), lista);	
	}

}
