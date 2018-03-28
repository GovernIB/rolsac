package es.caib.rolsac.apirest.v1.model.respuestas;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty(value = "Mensaje", dataType = "java.lang.String", required = false)
	private String mensaje;

	/** Numero de Elementos. **/
	@ApiModelProperty(value = "Numero de Elementos", dataType = "java.lang.Integer", required = true)
	private Integer numeroElementos;

	
	//private List<T> resultado;

	public RespuestaBase(String status, String mensaje, Integer numeroElementos) {
		super();
		this.status = status;
		this.mensaje = mensaje;
		this.numeroElementos = numeroElementos;
	}
	
	public RespuestaBase() {
		this.status = null;
		this.mensaje = null;
		this.numeroElementos = null;
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
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	
}
