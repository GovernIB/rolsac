package org.ibit.rol.sac.model;

public class ResultadoSiaPendiente {
	


	private boolean correcto;
	
	private String mensaje;

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
	
	public ResultadoSiaPendiente(boolean correcto, String mensaje) {
		super();
		this.correcto = correcto;
		this.mensaje = mensaje;
	}

	public ResultadoSiaPendiente() {
		super();		
	}
	
	

}
