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
 * FiltroUA.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "FiltroUA", description = "Filtro propio de la entidad Unidad Administrativa")
public class FiltroUA {
	 
	/** CodigoUAPadre. **/
	@ApiModelProperty(value = "Código Unidad Administrativa padre ", dataType = "java.lang.Integer", required = false)
	private Integer codigoUAPadre;

	/** Validacion. **/
	@ApiModelProperty(value = "Validacion", dataType = "java.lang.Integer", required = false)
	private Integer validacion;
	
	/** CodigoSeccion. **/
	@ApiModelProperty(value = "Código de sección", dataType = "java.lang.Integer", required = false)
	private Integer codigoSeccion;

	
	/** CodigoNormativa. **/
	@ApiModelProperty(value = "Código de Normativa", dataType = "java.lang.Integer", required = false)
	private Integer codigoNormativa;
	
	
	public boolean hayFiltro() {		
		return 	this.codigoUAPadre!=null || 
				this.validacion!=null || 
				this.codigoSeccion!=null || 
				this.codigoNormativa!=null;
	}
	
	
	
	public static FiltroUA valueOf(final String json) {
		final ObjectMapper objectMapper = new ObjectMapper();
		final TypeReference<FiltroUA> typeRef = new TypeReference<FiltroUA>() {
		};
		FiltroUA obj;
		try {
			obj = (FiltroUA) objectMapper.readValue(json, typeRef);
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
	 * @return the codigoUAPadre
	 */
	public Integer getCodigoUAPadre() {
		return codigoUAPadre;
	}


	/**
	 * @param codigoUAPadre the codigoUAPadre to set
	 */
	public void setCodigoUAPadre(Integer codigoUAPadre) {
		this.codigoUAPadre = codigoUAPadre;
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
	 * @return the codigoNormativa
	 */
	public Integer getCodigoNormativa() {
		return codigoNormativa;
	}


	/**
	 * @param codigoNormativa the codigoNormativa to set
	 */
	public void setCodigoNormativa(Integer codigoNormativa) {
		this.codigoNormativa = codigoNormativa;
	}
	
}
