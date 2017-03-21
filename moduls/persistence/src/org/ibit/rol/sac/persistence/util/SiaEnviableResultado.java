package org.ibit.rol.sac.persistence.util;

/**
 * Clase que indica si un procedimiento es enviable a SIA y en caso de no, envía el texto
 *   con la respuesta incorrecta.
 * @author slromero
 *
 */
public class SiaEnviableResultado {
	/** Resultado de si tiene que notificar a SIA. **/
	private boolean notificarSIA;
	/** Respuesta del porque NO. **/
	private String respuesta;
	/** Tipo de operación que se debe realizar. **/
	private String operacion;
	/** Id centro. **/
	private String idCentro;
	
	/** Constructor. **/
	public SiaEnviableResultado(boolean iEnviable, String iRespuesta) {
		this.notificarSIA = iEnviable;
		this.respuesta = iRespuesta;
	}

	/** Constructor. **/
	public SiaEnviableResultado(boolean iEnviable) {
		this.notificarSIA = iEnviable;
		this.respuesta = "";
	}

	/**
	 * @return the notificarSIA
	 */
	public boolean isNotificiarSIA() {
		return notificarSIA;
	}
	
	/**
	 * @param notificarSIA the notificarSIA to set
	 */
	public void setNotificarSIA(boolean notificarSIA) {
		this.notificarSIA = notificarSIA;
	}
	
	/**
	 * @return the respuesta
	 */
	public String getRespuesta() {
		return respuesta;
	}
	
	/**
	 * @param respuesta the respuesta to set
	 */
	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the idCentro
	 */
	public String getIdCentro() {
		return idCentro;
	}

	/**
	 * @param idCentro the idCentro to set
	 */
	public void setIdCentro(String idCentro) {
		this.idCentro = idCentro;
	}
	
}
