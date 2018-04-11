package es.caib.rolsac.apirest.v1.model.filtros;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import org.ibit.rol.sac.model.filtro.FiltroGenerico;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * FiltroUA.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroAgrupacioFetVital", description = "Filtro propio de la entidad AgrupacioFetVital")
public class FiltroAgrupacioFetVital {
	
	public static final String SAMPLE = "{\"publico\":\"0\"}";
	
	 
	/** CodigoUAPadre. **/
	@ApiModelProperty(value = "Código Publico Objetivo ", dataType = "java.lang.Integer", required = false)
	private Integer publico;
	
		
	public static FiltroAgrupacioFetVital valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroAgrupacioFetVital> typeRef = new TypeReference<FiltroAgrupacioFetVital>() {
		};
		FiltroAgrupacioFetVital obj;
		try {
			obj = (FiltroAgrupacioFetVital) objectMapper.readValue(json, typeRef);
		} catch (final IOException e) {
			// TODO PENDIENTE
			throw new RuntimeException(e);
		}
		return obj;
	}
	
	
	public FiltroGenerico toFiltroGenerico() {
		FiltroGenerico fg = new FiltroGenerico();
		if(this.publico!= null) {
			fg.addFiltro(FiltroGenerico.FILTRO_AFV_PUBLICO, this.publico+"");
		}
		
		return fg;
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
	 * @return the publico
	 */
	public Integer getPublico() {
		return publico;
	}


	/**
	 * @param publico the publico to set
	 */
	public void setPublico(Integer publico) {
		this.publico = publico;
	}

}
