package es.caib.rolsac.apirest.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.QueryParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MediaType;

import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.model.Idioma;
import es.caib.rolsac.apirest.v1.model.Respuesta;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path( "/idiomas" ) 
@Api( value = "/idiomas",   tags = "idiomas"  )
public class IdiomaService {

	
	
	/**
	 * Listado de idiomas.
	 * @param ua
	 * @param idioma
	 * @param page
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/")
	@ApiOperation( 
	    value = "Listar todos los idiomas", 
	    response = Idioma.class, 
	    responseContainer = "List"
	)
	
	public List<Idioma> llistarIdiomas(@QueryParam("idioma") final Idioma idiomaEntrada) throws DelegateException {

		final List<Idioma> idiomas = new ArrayList<Idioma>();
		if(idiomaEntrada==null) {
			idiomas.add(new Idioma());
		}else {
			idiomas.add(idiomaEntrada);
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
		
		return idiomas;
	}
	
	
	
	
	
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/Respuesta")
	@ApiOperation( 
	    value = "Listar todos los idiomas", 
	    response = Respuesta.class
	)
	
	public Respuesta llistarIdiomasRes(@QueryParam("idioma") final Idioma idiomaEntrada) throws DelegateException {

		final List<Idioma> idiomas = new ArrayList<Idioma>();
		if(idiomaEntrada!=null) {
			idiomas.add(idiomaEntrada);
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
			
		Respuesta r = new Respuesta("200", null, null, 1, idiomas);
		
		return r;
	}
	
	
	/**
	 * Listado de idiomas.
	 * @param ua
	 * @param idioma
	 * @param page
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes("application/json")
	@Path("/")
	@ApiOperation( 
	    value = "Listar todos los idiomas", 
	    response = Respuesta.class
	)
	
	
	public Respuesta llistarIdiomasP(
			@FormParam("idioma") final Idioma idiomaEntrada,
			@FormParam("idioma2") final Idioma idiomaEntrada2) throws DelegateException {

		final List<Idioma> idiomas = new ArrayList<Idioma>();
		if(idiomaEntrada==null) {
			idiomas.add(new Idioma());
		}else {
			idiomas.add(idiomaEntrada);
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
		
		Respuesta r = new Respuesta("200", null, null, 1, idiomas);
		
		return r;
	}
	
	
	
	
	
	
	/**
	 * Para obtener el idioma.
	 * 
	 * @param idioma 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/{lang}")
	@ApiOperation( 
	    value = "Obtiene el idioma", 
	    response = Idioma.class
	)
	public Idioma  obtenirV1(  
			@ApiParam( value = "lang", required = false, defaultValue = Constantes.IDIOMA_DEFECTO ) 
			@PathParam( "lang") final String lang) throws Exception {

		
		org.ibit.rol.sac.model.Idioma rIdioma = DelegateUtil.getIdiomaDelegate().obtenerIdioma(lang);
		final Idioma idioma = new Idioma();
		idioma.setCodigoEstandar(rIdioma.getCodigoEstandar());
		idioma.setLang(rIdioma.getLang());
		idioma.setLangTraductor(rIdioma.getLangTraductor());
		idioma.setNombre(rIdioma.getNombre());
		idioma.setOrden(rIdioma.getOrden());
		
		return idioma;
		
	}
	
	
	
	
	
	
	
}
