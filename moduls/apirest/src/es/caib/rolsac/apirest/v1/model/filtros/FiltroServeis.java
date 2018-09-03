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
 * FiltroServicios.
 * 
 * @author Indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroServicios", description = "Filtro que permite buscar por diferentes campos")
public class FiltroServeis {
	
	public static final String SAMPLE = 
		     Constantes.SALTO_LINEA + "{\"codigoUA\":\"0\"," +
		     Constantes.SALTO_LINEA + "\"activo\":\"0/1\","
		    + Constantes.SALTO_LINEA + "\"estadoUA\":\"0\","
		    + Constantes.SALTO_LINEA + "\"codigoAgrupacionHechoVital\":\"0\","
		    + Constantes.SALTO_LINEA + "\"codigoPublicoObjetivo\":\"0\","
		  //  + Constantes.SALTO_LINEA + "\"codigoFamilia\":\"0\","
		    + Constantes.SALTO_LINEA + "\"codigoAgrupacionMateria\":\"0\","
			+ Constantes.SALTO_LINEA + "\"codigoMateria\":\"0\","
			+ Constantes.SALTO_LINEA + "\"textos\":\"texto\""
			+ Constantes.SALTO_LINEA + "\"fechaPublicacionDesde\":\"DD/MM/YYYY\","
			+ Constantes.SALTO_LINEA + "\"fechaPublicacionHasta\":\"DD/MM/YYYY\","
		//	+ Constantes.SALTO_LINEA + "\"vigente\":\"0/1\","
		//	+ Constantes.SALTO_LINEA + "\"telematico\":\"0/1\","
			+ Constantes.SALTO_LINEA + "\"codigoSia\":\"codigo\","
			+ Constantes.SALTO_LINEA + "\"estadoSia\":\"0\","
			+ Constantes.SALTO_LINEA + "\"fechaActualizacionSia\":\"DD/MM/YYYY\""
		    + "}";
	
	/** codigoUA. **/
	@ApiModelProperty(value = "codigoUA", required = false)
	private Integer codigoUA; 
	
	/** activo. **/
	@ApiModelProperty(value = "activo", required = false)
	private Integer activo; 
	
	/** estadoUA. **/
	@ApiModelProperty(value = "estadoUA- validacion ua", required = false)
	private Integer estadoUA; 
	
	/** codigoAgrupacionHechoVital. **/
	@ApiModelProperty(value = "codigoAgrupacionHechoVital", required = false)
	private Integer codigoAgrupacionHechoVital; 
	
	/** codigoPublicoObjetivo. **/
	@ApiModelProperty(value = "codigoPublicoObjetivo", required = false)
	private Integer codigoPublicoObjetivo; 
	
	/** codigoFamilia. **/
	@ApiModelProperty(value = "codigoFamilia", required = false)
	private Integer codigoFamilia; 
	
	/** codigoAgrupacionMateria. **/
	@ApiModelProperty(value = "codigoAgrupacionMateria", required = false)
	private Integer codigoAgrupacionMateria; 
	
	/** codigoMateria. **/
	@ApiModelProperty(value = "codigoMateria", required = false)
	private Integer codigoMateria; 
	
	/** textos. **/
	@ApiModelProperty(value = "textos", required = false)
	private String textos; 
		
	/** fechaPublicacionDesde. **/
	@ApiModelProperty(value = "fechaPublicacionDesde", required = false)
	private String fechaPublicacionDesde;

	/** fechaPublicacionHasta. **/
	@ApiModelProperty(value = "fechaPublicacionHasta", required = false)
	private String fechaPublicacionHasta;
	
	/** vigente. **/
	@ApiModelProperty(value = "vigente", required = false)
	private Integer vigente; 
	
	/** telematico. **/
	@ApiModelProperty(value = "telematico", required = false)
	private Integer telematico; 
	
	/** codigoSia. **/
	@ApiModelProperty(value = "codigoSia", required = false)
	private String codigoSia; 
		
	/** estadoSia. **/
	@ApiModelProperty(value = "estadoSia", required = false)
	private String estadoSia; 
	
	/** fechaActualizacionSia. **/
	@ApiModelProperty(value = "fechaActualizacionSia", required = false)
	private String fechaActualizacionSia;
	
	
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();

		if(this.codigoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_UA, this.codigoUA+"");
		}
		
		if(this.activo!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_ACTIVO, this.activo+"");
		}
		
		if(this.estadoUA!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_ESTADO_UA, this.estadoUA+"");
		}
		
		if(this.codigoAgrupacionHechoVital!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_AGRUPACION_HECHO_VITAL, this.codigoAgrupacionHechoVital+"");
		}
		
		if(this.codigoPublicoObjetivo!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_PUBLICO, this.codigoPublicoObjetivo+"");
		}
		
		if(this.codigoFamilia!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_FAMILIA, this.codigoFamilia+"");
		}
		
		if(this.codigoAgrupacionMateria!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_AGRUPACION_MATERIA, this.codigoAgrupacionMateria+"");
		}
		
		if(this.codigoMateria!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_MATERIA, this.codigoMateria+"");
		}
		
		if(this.textos!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_TEXTOS, this.textos+"");
		}
		
		if(this.fechaPublicacionDesde!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_FECHA_PUBLICACION_DESDE, this.fechaPublicacionDesde+"");
		}
		
		if(this.fechaPublicacionHasta!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_FECHA_PUBLICACION_HASTA, this.fechaPublicacionHasta+"");
		}
		
		if(this.vigente!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_VIGENTE, this.vigente+"");
		}
		
		if(this.telematico!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_TELEMATICO, this.telematico+"");
		}
		
		if(this.codigoSia!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_CODIGO_SIA, this.codigoSia+"");
		}
		
		if(this.estadoSia!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_ESTADO_SIA, this.estadoSia+"");
		}
		
		if(this.fechaActualizacionSia!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_SERVICIOS_FECHA_ACTUALIZACION_SIA, this.fechaActualizacionSia+"");
		}
		
		return fg;
	}
	
	
	
	public static FiltroServeis valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroServeis> typeRef = new TypeReference<FiltroServeis>() {
		};
		FiltroServeis obj;
		try {
			obj = (FiltroServeis) objectMapper.readValue(json, typeRef);
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
	 * @return the estadoUA
	 */
	public Integer getEstadoUA() {
		return estadoUA;
	}



	/**
	 * @param estadoUA the estadoUA to set
	 */
	public void setEstadoUA(Integer estadoUA) {
		this.estadoUA = estadoUA;
	}



	/**
	 * @return the codigoAgrupacionHechoVital
	 */
	public Integer getCodigoAgrupacionHechoVital() {
		return codigoAgrupacionHechoVital;
	}



	/**
	 * @param codigoAgrupacionHechoVital the codigoAgrupacionHechoVital to set
	 */
	public void setCodigoAgrupacionHechoVital(Integer codigoAgrupacionHechoVital) {
		this.codigoAgrupacionHechoVital = codigoAgrupacionHechoVital;
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



	/**
	 * @return the codigoFamilia
	 */
	public Integer getCodigoFamilia() {
		return codigoFamilia;
	}



	/**
	 * @param codigoFamilia the codigoFamilia to set
	 */
	public void setCodigoFamilia(Integer codigoFamilia) {
		this.codigoFamilia = codigoFamilia;
	}



	/**
	 * @return the codigoAgrupacionMateria
	 */
	public Integer getCodigoAgrupacionMateria() {
		return codigoAgrupacionMateria;
	}



	/**
	 * @param codigoAgrupacionMateria the codigoAgrupacionMateria to set
	 */
	public void setCodigoAgrupacionMateria(Integer codigoAgrupacionMateria) {
		this.codigoAgrupacionMateria = codigoAgrupacionMateria;
	}



	/**
	 * @return the codigoMateria
	 */
	public Integer getCodigoMateria() {
		return codigoMateria;
	}



	/**
	 * @param codigoMateria the codigoMateria to set
	 */
	public void setCodigoMateria(Integer codigoMateria) {
		this.codigoMateria = codigoMateria;
	}



	/**
	 * @return the textos
	 */
	public String getTextos() {
		return textos;
	}



	/**
	 * @param textos the textos to set
	 */
	public void setTextos(String textos) {
		this.textos = textos;
	}



	/**
	 * @return the fechaPublicacionDesde
	 */
	public String getFechaPublicacionDesde() {
		return fechaPublicacionDesde;
	}



	/**
	 * @param fechaPublicacionDesde the fechaPublicacionDesde to set
	 */
	public void setFechaPublicacionDesde(String fechaPublicacionDesde) {
		this.fechaPublicacionDesde = fechaPublicacionDesde;
	}



	/**
	 * @return the fechaPublicacionHasta
	 */
	public String getFechaPublicacionHasta() {
		return fechaPublicacionHasta;
	}



	/**
	 * @param fechaPublicacionHasta the fechaPublicacionHasta to set
	 */
	public void setFechaPublicacionHasta(String fechaPublicacionHasta) {
		this.fechaPublicacionHasta = fechaPublicacionHasta;
	}



	/**
	 * @return the vigente
	 */
	public Integer getVigente() {
		return vigente;
	}



	/**
	 * @param vigente the vigente to set
	 */
	public void setVigente(Integer vigente) {
		this.vigente = vigente;
	}



	/**
	 * @return the telematico
	 */
	public Integer getTelematico() {
		return telematico;
	}



	/**
	 * @param telematico the telematico to set
	 */
	public void setTelematico(Integer telematico) {
		this.telematico = telematico;
	}




	/**
	 * @return the fechaActualizacionSia
	 */
	public String getFechaActualizacionSia() {
		return fechaActualizacionSia;
	}

	/**
	 * @param fechaActualizacionSia the fechaActualizacionSia to set
	 */
	public void setFechaActualizacionSia(String fechaActualizacionSia) {
		this.fechaActualizacionSia = fechaActualizacionSia;
	}



	/**
	 * @return the estadoSia
	 */
	public String getEstadoSia() {
		return estadoSia;
	}



	/**
	 * @param estadoSia the estadoSia to set
	 */
	public void setEstadoSia(String estadoSia) {
		this.estadoSia = estadoSia;
	}



	/**
	 * @return the codigoSia
	 */
	public String getCodigoSia() {
		return codigoSia;
	}



	/**
	 * @param codigoSia the codigoSia to set
	 */
	public void setCodigoSia(String codigoSia) {
		this.codigoSia = codigoSia;
	}
	
}