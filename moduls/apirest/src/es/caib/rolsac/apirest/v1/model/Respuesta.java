package es.caib.rolsac.apirest.v1.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.lang.Object;
import java.util.List;
import java.io.IOException;

/**
 * Idioma.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "Respuesta", description = "Respuesta Base")
public class Respuesta {
	 


	/** Status a retornar. **/
	@ApiModelProperty(value = "Status", dataType = "java.lang.String", required = true)
	private String status;

	/** Código interno del error. **/
	@ApiModelProperty(value = "Código de  error ", dataType = "java.lang.String", required = false)
	private String codigoError;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Mensaje de  error ", dataType = "java.lang.String", required = false)
	private String mensajeError;

	/** Numero de Elementos. **/
	@ApiModelProperty(value = "Numero de Elementos", dataType = "java.lang.Integer", required = true)
	private Integer numeroElementos;

	/** Resultado. **/
	@ApiModelProperty(value = "Resultado", dataType = "es.caib.rolsac.apirest.v1.model.ModeloBase", required = false)
	private List<Idioma> resultado;

	public Respuesta(String status, String codigoError, String mensajeError, Integer numeroElementos,
			List<Idioma> resultado) {
		super();
		this.status = status;
		this.codigoError = codigoError;
		this.mensajeError = mensajeError;
		this.numeroElementos = numeroElementos;
		this.resultado = resultado;
	}
	
	public Respuesta() {
		this.status = null;
		this.codigoError = null;
		this.mensajeError = null;
		this.numeroElementos = null;
		this.resultado = null;
	};
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the codigoError
	 */
	public String getCodigoError() {
		return codigoError;
	}

	/**
	 * @param codigoError the codigoError to set
	 */
	public void setCodigoError(String codigoError) {
		this.codigoError = codigoError;
	}

	/**
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * @return the numeroElementos
	 */
	public Integer getNumeroElementos() {
		return numeroElementos;
	}

	/**
	 * @param numeroElementos the numeroElementos to set
	 */
	public void setNumeroElementos(Integer numeroElementos) {
		this.numeroElementos = numeroElementos;
	}

	/**
	 * @return the resultado
	 */
	public List<Idioma> getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(List<Idioma> resultado) {
		this.resultado = resultado;
	}


	
}
