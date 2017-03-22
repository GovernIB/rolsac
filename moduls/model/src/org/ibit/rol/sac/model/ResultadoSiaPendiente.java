package org.ibit.rol.sac.model;

/**
 * Resultado sia pendiente.
 * @author slromero
 *
 */
public class ResultadoSiaPendiente {
	
	/** Correcto. **/
	private boolean correcto;
	/** Mensaje. **/
	private String mensaje;
	/** Cuantos. **/
	private int cuantos;

	/**
	 * @return the correcto
	 */
	public boolean isCorrecto() {
		return correcto;
	}

	/**
	 * @param correcto the correcto to set
	 */
	public void setCorrecto(boolean correcto) {
		this.correcto = correcto;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	/** Constructor. **/
	public ResultadoSiaPendiente(boolean correcto, String mensaje) {
		super();
		this.correcto = correcto;
		this.mensaje = mensaje;
	}
	
	/** Constructor. **/
	public ResultadoSiaPendiente(boolean correcto, String mensaje, int cuantos) {
		super();
		this.correcto = correcto;
		this.mensaje = mensaje;
		this.cuantos = cuantos;
	}

	/** Constructor. **/
	public ResultadoSiaPendiente() {
		super();		
	}

	/**
	 * @return the cuantos
	 */
	public int getCuantos() {
		return cuantos;
	}

	/**
	 * @param cuantos the cuantos to set
	 */
	public void setCuantos(int cuantos) {
		this.cuantos = cuantos;
	}
	
	

}
