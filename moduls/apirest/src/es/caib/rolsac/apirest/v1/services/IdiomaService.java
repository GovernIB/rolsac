package es.caib.rolsac.apirest.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.validation.ValidationException;
import javax.validation.constraints.Size;
import javax.validation.Valid;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.Idioma;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaIdioma;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+Constantes.ENTIDAD_IDIOMA ) 
@Api( value = "/"+Constantes.ENTIDAD_IDIOMA,   tags = "idiomes"  )
public class IdiomaService {
		
		
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
	    value = "idiomes",
	    notes = "Lista todos los idiomas disponibles"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaIdioma.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	public RespuestaIdioma llistarIdiomasP() throws DelegateException,ExcepcionAplicacion,ValidationException  {
		final List<Idioma> idiomas = new ArrayList<Idioma>();
		final List<org.ibit.rol.sac.model.Idioma> rIdiomas = DelegateUtil.getIdiomaDelegate().listarIdiomas();
		for(org.ibit.rol.sac.model.Idioma rIdioma : rIdiomas) {
			Idioma idioma2 = new Idioma();
			idioma2.setCodigoEstandar(rIdioma.getCodigoEstandar());
			idioma2.setLang(rIdioma.getLang());
			idioma2.setLangTraductor(rIdioma.getLangTraductor());
			idioma2.setNombre(rIdioma.getNombre());
			idioma2.setOrden(rIdioma.getOrden());
			idiomas.add(idioma2);
		}				
		RespuestaIdioma r = new RespuestaIdioma(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(idiomas.size()) , idiomas.size(), idiomas);
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
	@Path("/{lang}")
	@ApiOperation( 
	    value = "idioma",
	    notes = "Obtiene el idioma especificado"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaIdioma.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	
	public RespuestaIdioma  getIdioma(  
			@ApiParam( value = "Codigo idioma (ej: es, ca, en)", required = false, defaultValue = Constantes.IDIOMA_DEFECTO )
		    @PathParam( "lang") final  String lang) throws Exception,ValidationException {
		
		final List<Idioma> idiomas = new ArrayList<Idioma>();		
		org.ibit.rol.sac.model.Idioma rIdioma = DelegateUtil.getIdiomaDelegate().obtenerIdioma(lang);
		if(rIdioma!=null) {
			final Idioma idioma = new Idioma();
			idioma.setCodigoEstandar(rIdioma.getCodigoEstandar());
			idioma.setLang(rIdioma.getLang());
			idioma.setLangTraductor(rIdioma.getLangTraductor());
			idioma.setNombre(rIdioma.getNombre());
			idioma.setOrden(rIdioma.getOrden());
			idioma.addLink(rIdioma.getCodigoEstandar(), Constantes.ENTIDAD_IDIOMA, Constantes.URL_IDIOMA,"");
			idiomas.add(idioma);
			
		}
		
		RespuestaIdioma r = new RespuestaIdioma(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(idiomas.size()) , idiomas.size(), idiomas);
		return r;
	}

}
