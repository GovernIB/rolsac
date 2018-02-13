package es.caib.rolsac.apirest.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.model.Fitxa;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@Path( "/fitxes" ) 
@Api( value = "/fitxes",  tags = "fitxes" )
public class FitxaService {

	
	/**
	 * Listado de Fitxas.
	 * @param ua
	 * @param idioma
	 * @param page
	 * @return
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/")
	@ApiOperation( 
	    value = "Listar todas las fichas", 
	    response = Fitxa.class, 
	    responseContainer = "List",
	    httpMethod = "GET"
	)
	public List< Fitxa > llistarFitxes(  
	        @ApiParam( value = "Id de la ua", required = true ) 
	        @QueryParam( "ua") final int ua,
	        
	        @ApiParam( value = "Idioma", required = true , defaultValue = Constantes.IDIOMA_DEFECTO) 
	        @QueryParam( "idioma") @DefaultValue( Constantes.IDIOMA_DEFECTO ) final String idioma,
	        
	        @ApiParam( value = "Page to fetch", required = true ) 
	        @QueryParam( "page") final int page
	       ) {

		List<Fitxa> fichas = new ArrayList<Fitxa>();
		Fitxa n1 = new Fitxa();
		n1.setCodigo(1l);
		n1.setTitulo("N1");
		
		Fitxa n2 = new Fitxa();
		n2.setCodigo(2l);
		n2.setTitulo("N2");
		
		fichas.add(n1);
		fichas.add(n2);
		return fichas;
		
		//DelegateUtil.getFichaDelegate().buscarFichas(ids)
		
	}
	
	/**
	 * Para obtener la ficha.
	 * 
	 * @param idioma 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/{id}")
	@ApiOperation( 
			value = "Obtiene la ficha con la id",   
		     response = Fitxa.class,
		     httpMethod = "GET"
	)
	public Fitxa  getFitxaById(  @ApiParam( value = "id", required = true ) 
							@PathParam( "id") final Long id) throws Exception {
		return this.getFitxaByIdLang(id, Constantes.IDIOMA_DEFECTO);
	}
	
	
	/**
	 * Para obtener la ficha.
	 * 
	 * @param lang 
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/{id}/{lang}")
	@ApiOperation( 
			value = "Obtiene la ficha con la id",   
		    response = Fitxa.class,
		    httpMethod = "GET"
	)
	public Fitxa  getFitxaByIdLang(  
			@ApiParam( value = "id", required = true ) 
			@PathParam( "id") final Long id,
			
			@ApiParam( value = "lang", required = true ) 
			@PathParam( "lang") @DefaultValue( Constantes.IDIOMA_DEFECTO ) final String lang
	         ) throws Exception {

		//TODO Quitar el defaultValue de la ID
		if (id == null) { throw new Exception("El parámetro id es obligatorio"); }
		org.ibit.rol.sac.model.Ficha ficha = DelegateUtil.getFichaDelegate().obtenerFicha(id);
		
		if (ficha == null) {
			throw new Exception("No existe el objeto");
		}
		
		Fitxa f1 = new Fitxa();
		f1.setCodigo(ficha.getId());
		org.ibit.rol.sac.model.TraduccionFicha traduccion = (org.ibit.rol.sac.model.TraduccionFicha) ficha.getTraduccion(lang);
		if (traduccion == null) {
			traduccion = (org.ibit.rol.sac.model.TraduccionFicha) ficha.getTraduccion(Constantes.IDIOMA_DEFECTO);
		}
		 		
		if (traduccion != null) {
			f1.setTitulo(traduccion.getTitulo());
			f1.setDescripcion(traduccion.getDescripcion());
			f1.setDescripcionAbr(traduccion.getDescAbr());
			f1.setUrl(traduccion.getUrl());
			
		}
		 
		f1.setFechaCaducidad(ficha.getFechaCaducidad());
		f1.setFechaActualizacion(ficha.getFechaActualizacion());
		f1.setFechaPublicacion(ficha.getFechaPublicacion());
		
		List<Long> hechosVitales = new ArrayList<Long>();
		if (ficha.getHechosVitales() != null) {
			for(org.ibit.rol.sac.model.HechoVital hechoVital  : ficha.getHechosVitales()) {
				if (hechoVital != null) {
					hechosVitales.add(hechoVital.getId());
				}
			}
		}
		f1.setHechosVitales(hechosVitales);
		f1.setInfo(ficha.getInfo());
		
		List<Long> publicosObjetivo = new ArrayList<Long>();
		if (ficha.getPublicosObjetivo() != null) {
			for(org.ibit.rol.sac.model.PublicoObjetivo publicoObjetivo  : ficha.getPublicosObjetivo()) {
				if (publicoObjetivo != null) {
					publicosObjetivo.add(publicoObjetivo.getId());
				}
			}
		}
		f1.setPublicosObjetivo(publicosObjetivo);
		f1.setResponsable(ficha.getResponsable());
		f1.setValidacion(ficha.getValidacion());
		
        List<Long> documentos = new ArrayList<Long>();
        if (ficha.getDocumentos() != null) {
        	for(org.ibit.rol.sac.model.Documento documentoFicha : ficha.getDocumentos()) {
        		if (documentoFicha != null) {
        			documentos.add(documentoFicha.getId());
        		}
        	}
        }
        f1.setDocumentos(documentos);
        
		return f1;
	}
}
