package org.ibit.rol.sac.model;

import java.util.Date;

public class EstadoProcesoSIA {
	
	private Date fechaInicio;
	
	private Date fechaFin;
	
	private int total;
	
	private int correctos;
	
	private int incorrectos;
	
	private int nulos;
	
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
	 * @return the nulos
	 */
	public int getNulos() {
		return nulos;
	}

	/**
	 * @param nulos the nulos to set
	 */
	public void setNulos(int nulos) {
		this.nulos = nulos;
	}
	
	
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
	
	public void addCorrecto() {
		correctos++;
	}
	
	public void addIncorrecto() {
		incorrectos++;
	}
	
	public void addNulo() {
		nulos++;
	}

}
