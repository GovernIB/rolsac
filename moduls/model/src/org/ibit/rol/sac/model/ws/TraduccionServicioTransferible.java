package org.ibit.rol.sac.model.ws;

import java.io.Serializable;

/**
 * Clase que representa la información traducida a transferir de un Servicio(PORMAD)
 */
public class TraduccionServicioTransferible extends AbstractTraduccion implements Serializable {

	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** Nombre. **/
	private String nombre;
	/** Resumen. **/
    private String resumen;
    /** Destinatarios. **/
    private String destinatarios;
    /** Requisitos. **/
    private String requisitos;
    /** Observaciones. **/
    private String observaciones;
    /** Objeto. **/
    private String objeto;
    /** Codigo estandar idioma. **/
    private String codigoEstandarIdioma;

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
	 * @return the destinatarios
	 */
	public String getDestinatarios() {
		return destinatarios;
	}
	/**
	 * @param destinatarios the destinatarios to set
	 */
	public void setDestinatarios(String destinatarios) {
		this.destinatarios = destinatarios;
	}
	/**
	 * @return the requisitos
	 */
	public String getRequisitos() {
		return requisitos;
	}
	/**
	 * @param requisitos the requisitos to set
	 */
	public void setRequisitos(String requisitos) {
		this.requisitos = requisitos;
	}
	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * @return the objeto
	 */
	public String getObjeto() {
		return objeto;
	}
	/**
	 * @param objeto the objeto to set
	 */
	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}
	/**
	 * @return the codigoEstandarIdioma
	 */
	public String getCodigoEstandarIdioma() {
		return codigoEstandarIdioma;
	}
	/**
	 * @param codigoEstandarIdioma the codigoEstandarIdioma to set
	 */
	public void setCodigoEstandarIdioma(String codigoEstandarIdioma) {
		this.codigoEstandarIdioma = codigoEstandarIdioma;
	}
	

}
