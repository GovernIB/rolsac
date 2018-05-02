package es.caib.rolsac.apirest.v1.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ValidationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.ibit.rol.sac.model.FichaUA;
import org.ibit.rol.sac.model.filtro.FiltroGenerico;
import org.ibit.rol.sac.persistence.delegate.DelegateException;
import org.ibit.rol.sac.persistence.delegate.DelegateUtil;

import es.caib.rolsac.apirest.v1.exception.ExcepcionAplicacion;
import es.caib.rolsac.apirest.v1.model.FitxesUA;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroFitxesUA;
import es.caib.rolsac.apirest.v1.model.filtros.FiltroPaginacion;
import es.caib.rolsac.apirest.v1.model.orden.CampoOrden;
import es.caib.rolsac.apirest.v1.model.orden.Orden;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaError;
import es.caib.rolsac.apirest.v1.model.respuestas.RespuestaFitxesUA;
import es.caib.rolsac.apirest.v1.utiles.Constantes;
import es.caib.rolsac.apirest.v1.utiles.Utiles;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path( "/"+ Constantes.ENTIDAD_FICHAUA ) 
@Api( value = "/"+ Constantes.ENTIDAD_FICHAUA,   tags = Constantes.ENTIDAD_FICHAUA  )
public class FitxesUAService {
		
		
	/**
	 * Listado de FitxesUA.
	 * @return
	 * @throws DelegateException 
	 */
	@Produces( { MediaType.APPLICATION_JSON } )
	@POST
	@Consumes({MediaType.APPLICATION_JSON , MediaType.APPLICATION_FORM_URLENCODED })
	@Path("/")
	@ApiOperation( 
	    value = "Lista las fichasUA",
	    notes = "Lista las fichasUA disponibles en funcion de los filtros"
	)
	@ApiResponses(value = { 
			 @ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaFitxesUA.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
	
	public RespuestaFitxesUA llistar(
	//	@ApiParam( value = "Codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang,		
		@ApiParam( value = "Filtro de Paginación: " + FiltroPaginacion.SAMPLE) @FormParam("filtroPaginacion") FiltroPaginacion filtroPaginacion,
		@ApiParam( value = "Filtro de fichasUA: " + FiltroFitxesUA.SAMPLE) @FormParam("filtro") FiltroFitxesUA filtro,
		@ApiParam( value = "Filtro de Orden: " + Orden.SAMPLE_ORDEN_FICHAUA) @FormParam("orden") Orden orden						
			) throws DelegateException,ExcepcionAplicacion,ValidationException  {
						
		if(filtro==null) {
			filtro = new FiltroFitxesUA(); 
		}
		FiltroGenerico fg = filtro.toFiltroGenerico();
		
	/*	if(lang!=null) {
			fg.setLang(lang);	
		}
		*/		
		//si no vienen los filtros se completan con los datos por defecto
		if(filtroPaginacion!=null) {
			fg.setPageSize(filtroPaginacion.getSize());
			fg.setPage(filtroPaginacion.getPage());
		}
		
		// si viene el orden intentamos rellenarlo
		if(orden!=null) {
			List<CampoOrden> ord = orden.getListaOrden();
			if(ord!=null && ord.size()>0) {
				for (CampoOrden campoOrden : ord) {
					if(campoOrden.getCampo().equals(Orden.CAMPO_ORD_FICHAUA_ORDEN) || campoOrden.getCampo().equals(Orden.CAMPO_ORD_FICHAUA_ORDEN_SECCION)) {
						fg.addOrden(campoOrden.getCampo(), campoOrden.getTipoOrden());	
					}
				}
			}		
		}
						
		return getRespuesta(fg);
	}
	
	
	/**
	 * Para obtener una fichaUA.
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
	    value = "Obtiene una fichaUA",
	    notes = "Obtiene La fichaUA con el código indicado"
	)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message =  Constantes.MSJ_200_GENERICO, response = RespuestaFitxesUA.class),
			@ApiResponse(code = 400, message = Constantes.MSJ_400_GENERICO, response = RespuestaError.class)
		   })
		
	public RespuestaFitxesUA  getPorId(  
			@ApiParam( value = "Codigo ficha", required = true ) @PathParam( "codigo") final  String codigo//,
		   // @ApiParam( value = "codigo de idioma", required = false ) @DefaultValue(Constantes.IDIOMA_DEFECTO) @FormParam("lang") final String lang
			) throws Exception,ValidationException {

		FiltroGenerico fg = new FiltroGenerico();
		
	/*	if(lang!=null) {
			fg.setLang(lang);	
		}*/
		fg.setId(new Long(codigo));		
		
		return getRespuesta(fg);	
		
	}
	
	
    private RespuestaFitxesUA getRespuesta(FiltroGenerico fg) throws DelegateException {		
    	es.caib.rolsac.utils.ResultadoBusqueda resultadoBusqueda = DelegateUtil.getFichaDelegate().consultaFichasUA(fg);	
		List <FitxesUA> lista = new ArrayList <FitxesUA>();
			
		for (FichaUA nodo : Utiles.castList(FichaUA.class, resultadoBusqueda.getListaResultados())) {
			FitxesUA elemento = new FitxesUA(nodo,null,fg.getLang(),true);
			lista.add(elemento);
		}
		
		RespuestaFitxesUA r = new RespuestaFitxesUA(Response.Status.OK.getStatusCode()+"", Constantes.mensaje200(lista.size()) , new Integer(resultadoBusqueda.getTotalResultados()), lista);
		return r;
	}
	
	

}
