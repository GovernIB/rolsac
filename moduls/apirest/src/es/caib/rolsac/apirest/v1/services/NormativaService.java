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

import es.caib.rolsac.apirest.v1.model.Normativa;
import es.caib.rolsac.apirest.v1.model.NormativaDocumento;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Path( "/normativas" ) 
@Api( value = "/normativas",  tags = "normativas"  )
@Produces(MediaType.APPLICATION_JSON)
public class NormativaService {

	
	/**
	 * Listado de normativas.
	 * @param ua
	 * @param idioma
	 * @param page
	 * @return
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/")
	@ApiOperation( 
	    value = "Listar todas las normativas", 
	    response = Normativa.class, 
	    responseContainer = "List"
	)
	public List< Normativa > llistarNormativasV1(  
			@ApiParam( value = "lang", required = false, defaultValue = "ca" ) 
	        @QueryParam("lang") @DefaultValue("ca")  String lang,
	        
	        @ApiParam( value = "ua", required = false ) 
	        @QueryParam( "ua") final int ua,
	        
	        @ApiParam( value = "pagina numero", required = false, defaultValue = "0" ) 
	        @QueryParam( "pagina") @DefaultValue( "0" ) final int pagina,
	        
	        @ApiParam( value = "pagina elementos", required = false, defaultValue = "10" ) 
	        @QueryParam( "elementos") @DefaultValue( "10" ) final int elementos
	       ) {

		List<Normativa> normativas = new ArrayList<Normativa>();
		Normativa n1 = new Normativa();
		n1.setId(1l);
		n1.setTitulo("N1");
		
		Normativa n2 = new Normativa();
		n2.setId(2l);
		n2.setTitulo("N2");
		
		normativas.add(n1);
		normativas.add(n2);
		return normativas;
		
		//DelegateUtil.getNormativaDelegate().buscarNormativas(ids)
		
	}
	
	@Produces( { MediaType.APPLICATION_JSON } )
	@GET
	@Path("/{id}")
	@ApiOperation( 
	    value = "Obtiene la normativa con la id.",  
	    response = Normativa.class	)
	public Normativa  getNormativaByIdV1(  
			@ApiParam( value = "id", required = true ) 
			@PathParam( "id") final Long id
	        ) throws Exception {
		
		return this.getNormativaByIdLangV1(id, Constantes.IDIOMA_DEFECTO);
	}
	
	/**
	 * Para obtener la normativa.
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
		value = "Obtiene la normativa con la id y el lang.",   
	    response = Normativa.class
	)
	public Normativa  getNormativaByIdLangV1(  
			@ApiParam( value = "id", required = true ) 
			@PathParam( "id") final Long id,
	        
			@ApiParam( value = "lang", required = false, defaultValue = Constantes.IDIOMA_DEFECTO ) 
			@PathParam( "lang") @DefaultValue( Constantes.IDIOMA_DEFECTO ) final String lang
	        ) throws Exception {

		
		if (id == null) { throw new Exception("El parámetro id es obligatorio"); }
		org.ibit.rol.sac.model.Normativa normativa = DelegateUtil.getNormativaDelegate().obtenerNormativa(id);
		
		if (normativa == null) {
			throw new Exception("No existe el objeto");
		}
		
		Normativa n1 = new Normativa();
		n1.setId(normativa.getId());
		org.ibit.rol.sac.model.TraduccionNormativa traduccion = (org.ibit.rol.sac.model.TraduccionNormativa) normativa.getTraduccion(lang);
		if (traduccion == null) {
			traduccion = (org.ibit.rol.sac.model.TraduccionNormativa) normativa.getTraduccion(Constantes.IDIOMA_DEFECTO);
		}
		 		
		if (traduccion != null) {
			n1.setTitulo(traduccion.getTitulo());
			n1.setSeccion(traduccion.getSeccion());
			n1.setEnlace(traduccion.getEnlace());
			n1.setObservaciones(traduccion.getObservaciones());
			n1.setResponsable(traduccion.getResponsable());
		}
		 
		if (normativa.getBoletin() != null) {
			n1.setBoletin(normativa.getBoletin().getId());
		}
        
		n1.setCodiVuds(normativa.getCodiVuds());
        n1.setDescCodiVuds(normativa.getDescCodiVuds());
        n1.setFecha(normativa.getFecha());
        n1.setFechaBoletin(normativa.getFechaBoletin());
        n1.setRegistro(normativa.getRegistro());
        
        if (normativa.getTipo() != null) {
        	 n1.setTipo(normativa.getTipo().getId());
        }
        List<Long> uas = new ArrayList<Long>();
        if (normativa.getUnidadesnormativas() != null) {
	        for( org.ibit.rol.sac.model.UnidadNormativa ua : normativa.getUnidadesnormativas()) {
	        	if (ua != null) {
	        		uas.add(ua.getId());
	        	}
	        }
        }
        n1.setUnidadesAdministrativas(uas);
        n1.setValidacion(normativa.getValidacion());
        
        List<NormativaDocumento> documentos = new ArrayList<NormativaDocumento>();
        if (normativa.getDocumentos() != null) {
        	for(org.ibit.rol.sac.model.DocumentoNormativa documentoNormativa : normativa.getDocumentos()) {
        		if (documentoNormativa != null) {
        			NormativaDocumento documento = new NormativaDocumento();
        			
        			org.ibit.rol.sac.model.TraduccionDocumentoNormativa trad = (org.ibit.rol.sac.model.TraduccionDocumentoNormativa) documentoNormativa.getTraduccion(lang);
        			if (trad == null) {
        				trad = (org.ibit.rol.sac.model.TraduccionDocumentoNormativa) documentoNormativa.getTraduccion(Constantes.IDIOMA_DEFECTO);
        			}
        			
        			if (trad != null) {
	        			if (trad.getArchivo() != null) {
	        				documento.setArchivo(trad.getArchivo().getId());
	        			}
	        			documento.setTitulo(trad.getTitulo());
	        			documento.setDescripcion(trad.getDescripcion());
        			}
        			
        			documento.setOrden(documentoNormativa.getOrden());
        			documento.setTipo(documentoNormativa.getTipus());
        		
        			documentos.add(documento);
        		}
        	}
        }
        n1.setDocumentos(documentos);
        
		return n1;
	}
}
