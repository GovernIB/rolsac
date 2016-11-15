package org.ibit.rol.sac.model.ws;

public class SiaResultado {
	
	public static int RESULTADO_OK = 1;
	
	public static int RESULTADO_ERROR = -1;
	
	public int resultado; 
	
	public String mensaje;

	public SiaResultado(int pResultado, String pMensaje) {
		resultado = pResultado;
		mensaje = pMensaje;
	}

	/**
	 * @return the resultado
	 */
	public int getResultado() {
		return resultado;
	}

	/**
	 * @param resultado the resultado to set
	 */
	public void setResultado(int resultado) {
		this.resultado = resultado;
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

	public boolean isCorrecto() {
		if (resultado == SiaResultado.RESULTADO_OK) {
			return true;
		} else {
			return false;
		}
	} 
}
