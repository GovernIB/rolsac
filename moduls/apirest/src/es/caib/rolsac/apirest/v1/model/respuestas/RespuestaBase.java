package es.caib.rolsac.apirest.v1.model.respuestas;

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
import es.caib.rolsac.apirest.v1.model.Idioma;

/**
 * RespuestaBase. Estructura de respuesta que contiene la información comun a todas las respuestas.
 * 
 * @author indra
 *
 */
@XmlRootElement
@ApiModel(value = "RespuestaBase", description = "Respuesta Base")
public class RespuestaBase {
	 


	/** Status a retornar. **/
	@ApiModelProperty(value = "Status", dataType = "java.lang.String", required = true)
	private String status;
	
	/** Mensaje de  error. **/
	@ApiModelProperty(value = "Mensaje de  error ", dataType = "java.lang.String", required = false)
	private String mensajeError;

	/** Numero de Elementos. **/
	@ApiModelProperty(value = "Numero de Elementos", dataType = "java.lang.Integer", required = true)
	private Integer numeroElementos;

	
	//private List<T> resultado;

	public RespuestaBase(String status, String codigoError, String mensajeError, Integer numeroElementos) {
		super();
		this.status = status;
		this.mensajeError = mensajeError;
		this.numeroElementos = numeroElementos;
		//this.resultado = resultado;
	}
	
	public RespuestaBase() {
		this.status = null;
		this.mensajeError = null;
		this.numeroElementos = null;
		//this.resultado = null;
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
	/*public List<T> getResultado() {
		return resultado;
	}*/

	/**
	 * @param resultado the resultado to set
	 */
	/*public void setResultado(List<T> resultado) {
		this.resultado = resultado;
	}*/

	
}
