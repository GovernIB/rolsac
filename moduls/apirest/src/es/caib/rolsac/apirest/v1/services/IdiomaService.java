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
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaIdioma;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/idiomes" ) 
@Api( value = "/idiomes",   tags = "idiomes"  )
public class IdiomaService {
	
	/* *
	 * Listado de idiomas.
	 * @param ua
	 * @param idioma
	 * @param page
	 * @return
	 * @throws DelegateException 
	 
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes("application/json, multipart/form-data")
	@Path("/")
	@ApiOperation( 
	    value = "idiomes",
	    response = RespuestaIdioma.class,
	    notes = "Lista todos los idiomas disponibles"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO),
		    @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO)})
	
	public RespuestaIdioma llistarIdiomasP(
			@ApiParam(value = "Json de tipus idioma ej: {\"lang\":\"es\"}") @FormParam("idioma") final Idioma idiomaEntrada,
			@FormParam("idioma2") final RespuestaIdioma resp) throws DelegateException,ExcepcionAplicacion {

		final List<Idioma> idiomas = new ArrayList<Idioma>();
		if(idiomaEntrada==null) {
			idiomas.add(new Idioma());
		}else {
			if(idiomaEntrada.getLang().equals("er")) {
				throw new ExcepcionAplicacion(Response.Status.BAD_REQUEST, "el parametro lang no puedes ser er");
			}else {
				idiomas.add(idiomaEntrada);
			}			
		}
		
		final List<org.ibit.rol.sac.model.Idioma> rIdiomas = DelegateUtil.getIdiomaDelegate().listarIdiomas();
		for(org.ibit.rol.sac.model.Idioma rIdioma : rIdiomas) {
			Idioma idioma = new Idioma();
			idioma.setCodigoEstandar(rIdioma.getCodigoEstandar());
			idioma.setLang(rIdioma.getLang());
			idioma.setLangTraductor(rIdioma.getLangTraductor());
			idioma.setNombre(rIdioma.getNombre());
			idioma.setOrden(rIdioma.getOrden());
			idiomas.add(idioma);
		}				
		RespuestaIdioma r = new RespuestaIdioma(Response.Status.OK.toString(), Constantes.mensaje200(idiomas.size()) , idiomas.size(), idiomas);
		return r;
	}*/
	
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
	    response = RespuestaIdioma.class,
	    notes = "Lista todos los idiomas disponibles"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO),
		    @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO)})
	
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
	 * Listado de idiomas.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes("application/json, multipart/form-data")
	@Path("/test")
	@ApiOperation( 
	    value = "idiomes/test",
	    response = RespuestaIdioma.class,
	    notes = "Lista todos los idiomas disponibles"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO),
		    @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO)})
	
	public RespuestaIdioma llistarIdiomasPTest(
			@ApiParam(value = "Json de tipus idioma ej: {\"lang\":\"es\"}")
			@Valid 
			@FormParam("idioma") Idioma idioma
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {

		final List<Idioma> idiomas = new ArrayList<Idioma>();
		if(idioma!=null) {
			if(idioma.getLang().equals("er")) {
				throw new ExcepcionAplicacion(Response.Status.BAD_REQUEST.getStatusCode(), "el parametro lang no puedes ser er");
			}else if(idioma.getLang().equals("no")) {
				int a = 1/0; 		
			}else {
				idiomas.add(idioma);
			}			
		}
		
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
	    response = RespuestaIdioma.class,
	    notes = "Obtiene el idioma especificado"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO),
		    @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO)})
	
	public RespuestaIdioma  getIdioma(  
			@Size(min = 2, max = 2, message = "El código de idioma debe ser de 2 carácteres ej: (ca)") 
			@ApiParam( value = "Codigo idioma (ej: es, ca, en)", required = false, defaultValue = Constantes.IDIOMA_DEFECTO )
			@Valid
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
			idiomas.add(idioma);
		}
		
		RespuestaIdioma r = new RespuestaIdioma(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(idiomas.size()) , idiomas.size(), idiomas);
		return r;
	}

}
