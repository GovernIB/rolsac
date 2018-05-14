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
 * Fitxes.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_TASA, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_TASA)
public class Taxes extends EntidadBase {
	 
	
	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;
	
	@ApiModelProperty(value = "codificacio", required = false)
	private java.lang.String codificacio;

	@ApiModelProperty(value = "descripcio", required = false)
	private java.lang.String descripcio;

	@ApiModelProperty(value = "formaPagament", required = false)
	private java.lang.String formaPagament;

	@ApiModelProperty(value = "orden", required = false)
	private java.lang.Long orden;
	
		
	public Taxes (org.ibit.rol.sac.model.Taxa elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public Taxes() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {	}


	public static Taxes valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<Taxes> typeRef = new TypeReference<Taxes>() {
		};
		Taxes obj;
		try {
			obj = (Taxes) objectMapper.readValue(json, typeRef);
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
	public void setId(Long codigo) {
		this.codigo = codigo;		
	}

	/**
	 * @return the codigo
	 */
	public long getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the codificacio
	 */
	public java.lang.String getCodificacio() {
		return codificacio;
	}

	/**
	 * @param codificacio the codificacio to set
	 */
	public void setCodificacio(java.lang.String codificacio) {
		this.codificacio = codificacio;
	}

	/**
	 * @return the descripcio
	 */
	public java.lang.String getDescripcio() {
		return descripcio;
	}

	/**
	 * @param descripcio the descripcio to set
	 */
	public void setDescripcio(java.lang.String descripcio) {
		this.descripcio = descripcio;
	}

	/**
	 * @return the formaPagament
	 */
	public java.lang.String getFormaPagament() {
		return formaPagament;
	}

	/**
	 * @param formaPagament the formaPagament to set
	 */
	public void setFormaPagament(java.lang.String formaPagament) {
		this.formaPagament = formaPagament;
	}

	/**
	 * @return the orden
	 */
	public java.lang.Long getOrden() {
		return orden;
	}

	/**
	 * @param orden the orden to set
	 */
	public void setOrden(java.lang.Long orden) {
		this.orden = orden;
	}

}
