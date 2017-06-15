package org.ibit.rol.sac.persistence.util;

import org.ibit.rol.sac.model.SiaUA;


/**
 * Clase que indica si un procedimiento es enviable a SIA y en caso de no, envía el texto
 *   con la respuesta incorrecta.
 * @author slromero
 *
 */
public class SiaCumpleDatos {
	/** Resultado de si tiene que notificar a SIA. **/
	private boolean cumpleDatos;
	/** Respuesta del porque NO y que falta. **/
	private String respuesta;
	/** El resumen. **/
	private String resumen;
	/** El nombre. **/
	private String nombre;
	/** Sia UA. **/
	private SiaUA siaUA;
	
	/** Constructor. **/
	public SiaCumpleDatos(boolean iCumpleDatos, String iRespuesta) {
		this.setCumpleDatos(iCumpleDatos);
		this.setRespuesta(iRespuesta);
	}

	/** Constructor. **/
	public SiaCumpleDatos(boolean iCumpleDatos) {
		this.setCumpleDatos(iCumpleDatos);
		this.setRespuesta("");
	}
	
	/** Constructor. **/
	public SiaCumpleDatos(String iRespuesta) {
		this.setCumpleDatos(false);
		this.setRespuesta(iRespuesta);
	}

	/**
	 * @return the cumpleDatos
	 */
	public boolean isCumpleDatos() {
		return cumpleDatos;
	}

	/**
	 * @param cumpleDatos the cumpleDatos to set
	 */
	public void setCumpleDatos(boolean cumpleDatos) {
		this.cumpleDatos = cumpleDatos;
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
	 * @return the resumen
	 */
	public String getResumen() {
		return resumen;
	}

	/**
	 * @param resumen the resumen to set
	 */
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
