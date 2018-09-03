package es.caib.rolsac.apirest.v1.model;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import es.caib.rolsac.apirest.v1.utiles.Constantes;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Idioma.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_IDIOMA, description = Constantes.TXT_DEFINICION_CLASE + Constantes.ENTIDAD_IDIOMA)
public class Idioma extends EntidadBase {
	 
	/** Lang. **/
	@ApiModelProperty(value = "Lang (ca/es)", required = true)
	private String lang;

	/** Código estándar. **/
	@ApiModelProperty(value = "Código estandar", required = false)
	private String codigoEstandar;

	/** Orden. **/
	@ApiModelProperty(value = "Orden",  required = false)
	private Integer orden;

	/** Nombre. **/
	@ApiModelProperty(value = "Nombre", required = false)
	private String nombre;

	/** Traductor. **/
	@ApiModelProperty(value = "Idioma traductor",  required = false)
	private String langTraductor;

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the codigoEstandar
	 */
	public String getCodigoEstandar() {
		return codigoEstandar;
	}

	/**
	 * @param codigoEstandar the codigoEstandar to set
	 */
	public void setCodigoEstandar(String codigoEstandar) {
		this.codigoEstandar = codigoEstandar;
	}

	/**
	 * @return the orden
	 */
	public Integer getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the langTraductor
	 */
	public String getLangTraductor() {
		return langTraductor;
	}

	/**
	 * @param langTraductor the langTraductor to set
	 */
	public void setLangTraductor(String langTraductor) {
		this.langTraductor = langTraductor;
	}
	
	@Override
	public String toString() {
		return "Idioma. lang:" + lang;
	}
	
	
	public static Idioma valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Idioma> typeRef = new TypeReference<Idioma>() {
		};
		Idioma obj;
		try {
			obj = (Idioma) objectMapper.readValue(json, typeRef);
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

	@Override
	protected void addSetersInvalidos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void generaLinks(String urlBase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setId(Long codigo) {
		// TODO Auto-generated method stub
		
	}
	
}