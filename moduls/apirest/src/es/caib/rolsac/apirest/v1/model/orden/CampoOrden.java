package es.caib.rolsac.apirest.v1.model.orden;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import org.ibit.rol.sac.model.filtro.FiltroGenerico;

/**
 * FiltroUA.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "CampoOrden", description = "Campo a ordenar")
public class CampoOrden {
	 
	/** campo. **/
	@ApiModelProperty(value = "Campo", dataType = "java.lang.String", required = true)
	private String Campo;

	/** tipoOrden. **/
	@ApiModelProperty(value = "Tipo de ordenacion", dataType = "java.lang.String", required = true, allowableValues ="ASC,DESC")
	private String tipoOrden;
	
	
		
	public static CampoOrden valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<CampoOrden> typeRef = new TypeReference<CampoOrden>() {
		};
		CampoOrden obj;
		try {
			obj = (CampoOrden) objectMapper.readValue(json, typeRef);
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
	 * @return the campo
	 */
	public String getCampo() {
		return Campo;
	}


	/**
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		Campo = campo;
	}


	/**
	 * @return the tipoOrden
	 */
	public String getTipoOrden() {
		return tipoOrden;
	}


	/**
	 * @param tipoOrden the tipoOrden to set
	 */
	public void setTipoOrden(String tipoOrden) {
		this.tipoOrden = tipoOrden;
	}


}
