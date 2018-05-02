package es.caib.rolsac.apirest.v1.model.filtros;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * FiltroFitxes.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroFichas", description = "Filtro que permite buscar por diferentes campos")
public class FiltroFitxes {
	
	public static final String SAMPLE = 
			  Constantes.SALTO_LINEA + "{\"activo\":\"1|0\"," 
			+ Constantes.SALTO_LINEA + "\"codigoSeccion\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoUA\":\"0\","
			+ Constantes.SALTO_LINEA + "\"validacion\":\"0\","
			+ Constantes.SALTO_LINEA + "\"fechaPublicacion\":\"DD/MM/YYYY\","
			+ Constantes.SALTO_LINEA + "\"codigoHechosVitales\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoPublicoObjetivo\":\"0\""
		    + "}";
	 
	/** activo. 0/1**/
	@ApiModelProperty(value = "activo", required = false)
	private Integer activo;

	/** codigoSeccion. **/
	@ApiModelProperty(value = "codigoSeccion", required = false)
	private Integer codigoSeccion;

	/** codigoUA. **/
	@ApiModelProperty(value = "codigoUA", required = false)
	private Integer codigoUA;
	
	/** validacion. **/
	@ApiModelProperty(value = "validacion", required = false)
	private Integer validacion;
	
	/** fechaPublicacion. **/
	@ApiModelProperty(value = "fechaPublicacion", required = false)
	private String fechaPublicacion;
		
	/** codigoHechosVitales. **/
	@ApiModelProperty(value = "codigoHechosVitales", required = false)
	private Integer codigoHechosVitales;
	
	/** codigoPublicoObjetivo. **/
	@ApiModelProperty(value = "codigoPublicoObjetivo", required = false)
	private Integer codigoPublicoObjetivo;
	
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		
		if(this.activo!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHAS_ACTIVO, this.activo+"");
		}
		
		if(this.codigoSeccion!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHAS_SECCION, this.codigoSeccion+"");
		}
		
		
		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHAS_UA, this.codigoUA+"");
		}
		
		
		if(this.validacion!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHAS_VALIDACION, this.validacion+"");
		}
		
		
		if(this.fechaPublicacion!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHAS_FECHA_PUBLICACION, this.fechaPublicacion+"");
		}
		
		
		if(this.codigoHechosVitales!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHAS_HECHOS_VITALES, this.codigoHechosVitales+"");
		}
		
		
		if(this.codigoPublicoObjetivo!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_FICHAS_PUBLICO_OBJETIVO, this.codigoPublicoObjetivo+"");
		}
		
		return fg;
	}
	
	
	
	public static FiltroFitxes valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroFitxes> typeRef = new TypeReference<FiltroFitxes>() {
		};
		FiltroFitxes obj;
		try {
			obj = (FiltroFitxes) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}

	
	public String toJson() {
		try {
			final ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, false);
			return objectMapper.writeValueAsString(this);
		} catch (final JsonProcessingException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
	}


	/**
	 * @return the activo
	 */
	public Integer getActivo() {
		return activo;
	}


	/**
	 * @param activo the activo to set
	 */
	public void setActivo(Integer activo) {
		this.activo = activo;
	}


	/**
	 * @return the codigoSeccion
	 */
	public Integer getCodigoSeccion() {
		return codigoSeccion;
	}


	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(Integer codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}


	/**
	 * @return the codigoUA
	 */
	public Integer getCodigoUA() {
		return codigoUA;
	}


	/**
	 * @param codigoUA the codigoUA to set
	 */
	public void setCodigoUA(Integer codigoUA) {
		this.codigoUA = codigoUA;
	}


	/**
	 * @return the validacion
	 */
	public Integer getValidacion() {
		return validacion;
	}


	/**
	 * @param validacion the validacion to set
	 */
	public void setValidacion(Integer validacion) {
		this.validacion = validacion;
	}


	/**
	 * @return the fechaPublicacion
	 */
	public String getFechaPublicacion() {
		return fechaPublicacion;
	}


	/**
	 * @param fechaPublicacion the fechaPublicacion to set
	 */
	public void setFechaPublicacion(String fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}


	/**
	 * @return the codigoHechosVitales
	 */
	public Integer getCodigoHechosVitales() {
		return codigoHechosVitales;
	}


	/**
	 * @param codigoHechosVitales the codigoHechosVitales to set
	 */
	public void setCodigoHechosVitales(Integer codigoHechosVitales) {
		this.codigoHechosVitales = codigoHechosVitales;
	}


	/**
	 * @return the codigoPublicoObjetivo
	 */
	public Integer getCodigoPublicoObjetivo() {
		return codigoPublicoObjetivo;
	}


	/**
	 * @param codigoPublicoObjetivo the codigoPublicoObjetivo to set
	 */
	public void setCodigoPublicoObjetivo(Integer codigoPublicoObjetivo) {
		this.codigoPublicoObjetivo = codigoPublicoObjetivo;
	}	
	
	
}
