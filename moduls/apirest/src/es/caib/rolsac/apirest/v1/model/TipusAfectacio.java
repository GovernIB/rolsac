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
 * Tipos de afectacion.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = Constantes.ENTIDAD_TIPO_AFECTACION, description = Constantes.TXT_DEFINICION_CLASE +  Constantes.ENTIDAD_TIPO_AFECTACION)
public class TipusAfectacio extends EntidadBase {
	

	/** codigo **/
	@ApiModelProperty(value = "codigo", required = false)
	private long codigo;

	/** nombre **/
	@ApiModelProperty(value = "nombre", required = false)
	private String nombre;
	
	/** idioma **/
	@ApiModelProperty(value = "idioma", required = false)    
	private java.lang.String idioma;
		
	public TipusAfectacio (org.ibit.rol.sac.model.TipoAfectacion elem, String urlBase,String idioma,boolean hateoasEnabled ) {
		super( elem, urlBase, idioma, hateoasEnabled);
	}
	
	public TipusAfectacio() {
		super();
	}
	
	
	@Override
	public void generaLinks(String urlBase) {
		
	}


	public static TipusAfectacio valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<TipusAfectacio> typeRef = new TypeReference<TipusAfectacio>() {
		};
		TipusAfectacio obj;
		try {
			obj = (TipusAfectacio) objectMapper.readValue(json, typeRef);
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
	 * @return the idioma
	 */
	public java.lang.String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(java.lang.String idioma) {
		this.idioma = idioma;
	}


	
}
