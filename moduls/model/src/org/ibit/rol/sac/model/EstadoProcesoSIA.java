package org.ibit.rol.sac.model;

import java.util.Date;

/**
 * Estado proceso SIA.
 * @author slromero
 *
 */
public class EstadoProcesoSIA {
	/** Fecha inicio. ***/
	private Date fechaInicio;
	/** Fecha fin. **/
	private Date fechaFin;
	/** Total. **/
	private int total;
	/** Correctos. **/
	private int correctos;
	/** Incorrectos. **/
	private int incorrectos;
	/** No cumple datos. **/
	private int noCumpleDatos;
	/** No enviable. **/
	private int noEnviable;
	/** Detalle. **/ 
	private String detalle = "";
	
	/**
	 * @return the numeroProcedimientos
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param numeroProcedimientos the numeroProcedimientos to set
	 */
	public void setTotal(int numeroProcedimientos) {
		this.total = numeroProcedimientos;
	}

	/**
	 * @return the correctos
	 */
	public int getCorrectos() {
		return correctos;
	}

	/**
	 * @param correctos the correctos to set
	 */
	public void setCorrectos(int correctos) {
		this.correctos = correctos;
	}

	/**
	 * @return the incorrectos
	 */
	public int getIncorrectos() {
		return incorrectos;
	}

	/**
	 * @param incorrectos the incorrectos to set
	 */
	public void setIncorrectos(int incorrectos) {
		this.incorrectos = incorrectos;
	}

	/**
	 * @return the noCumpleDatos
	 */
	public int getNoCumpleDatos() {
		return noCumpleDatos;
	}

	/**
	 * @param noCumpleDatos the noCumpleDatos to set
	 */
	public void setNoCumpleDatos(int noCumpleDatos) {
		this.noCumpleDatos = noCumpleDatos;
	}

	/**
	 * @return the noEnviable
	 */
	public int getNoEnviable() {
		return noEnviable;
	}

	/**
	 * @param noEnviable the noEnviable to set
	 */
	public void setNoEnviable(int noEnviable) {
		this.noEnviable = noEnviable;
	}

	/**
	 * @return the detalle
	 */
	public String getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	/**
	 * Añade una nueva linea.
	 * @param linea
	 */
	public void addLineaDetalle(String linea) {
		detalle += linea + "\n";
	}

	/**
	 * @return the fechaInicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public Date getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/**
	 * Incrementa el total de correctos.
	 */
	public void addCorrecto() {
		correctos++;
	}
	
	/**
	 * Incrementa el total de incorrectos.
	 */
	public void addIncorrecto() {
		incorrectos++;
	}
	
	/**
	 * Incrementa el total de no cumple datos.
	 */
	public void addNoCumpleDatos() {
		noCumpleDatos++;
	}
	
	/**
	 * Incrementa el total de no enviables.
	 */
	public void addNoEnviable() {
		noEnviable++;
	}
	
}
