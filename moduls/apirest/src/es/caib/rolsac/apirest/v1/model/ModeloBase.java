package es.caib.rolsac.apirest.v1.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * Idioma.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "ModeloBase", description = "Definición del modelo Base")
public abstract class ModeloBase {
	 
	/** Lang. **/
	@ApiModelProperty(value = "codigo", dataType = "java.lang.String", required = true)
	private String codigo;

	

	/**
	 * @return the lang
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	public static ModeloBase valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<ModeloBase> typeRef = new TypeReference<ModeloBase>() {
		};
		ModeloBase obj;
		try {
			obj = (ModeloBase) objectMapper.readValue(json, typeRef);
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
	
}
