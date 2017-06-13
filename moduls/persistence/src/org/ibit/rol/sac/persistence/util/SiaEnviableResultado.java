package org.ibit.rol.sac.persistence.util;

import org.ibit.rol.sac.model.SiaUA;


/**
 * Clase que indica si un procedimiento es enviable a SIA y en caso de no, envía el texto
 *   con la respuesta incorrecta.
 * Se incluye también:
 * <ul>
 * 	<li>Id centro (código DIR3) a partir del órgano resolutorio y sus descendientes.</li>
 *  <li>Sia UA (Multientidad).</li>
 * </ul>
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
	/** Sia UA. **/
	private SiaUA siaUA;
	
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

	/**
	 * @return the siaUA
	 */
	public SiaUA getSiaUA() {
		return siaUA;
	}

	/**
	 * @param siaUA the siaUA to set
	 */
	public void setSiaUA(SiaUA siaUA) {
		this.siaUA = siaUA;
	}

	
}
