package es.caib.rolsac.apirest.v1.model.filtros;

import java.io.IOException;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * FiltroPaginacion.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroPaginacion", description = "Filtro que permite paginar los resultados")
public class FiltroPaginacion {
	 
	/** Page. **/
	@ApiModelProperty(value = "Page", dataType = "java.lang.Integer", required = false)
	private Integer page;

	/** Size. **/
	@ApiModelProperty(value = "Size", dataType = "java.lang.Integer", required = false)
	private Integer size;

	
	
	
	public static FiltroPaginacion valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroPaginacion> typeRef = new TypeReference<FiltroPaginacion>() {
		};
		FiltroPaginacion obj;
		try {
			obj = (FiltroPaginacion) objectMapper.readValue(json, typeRef);
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
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}


	/**
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}


	/**
	 * @param size the size to set
	 */
	public void setSize(Integer size) {
		this.size = size;
	}
	
}
