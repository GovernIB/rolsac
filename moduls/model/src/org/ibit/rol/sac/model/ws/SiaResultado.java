package org.ibit.rol.sac.model.ws;

public class SiaResultado {
	
	public static int RESULTADO_OK = 1;
	
	public static int RESULTADO_ERROR = -1;
	
	public int resultado; 
	
	public String mensaje;
	
	public int correctos = 0;
	
	public int incorrectos = 0;
	
	public String codSIA;
	
	public String estadoSIA;
	
	public SiaResultado() {
		
	}

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
	 * @return the codSIA
	 */
	public String getCodSIA() {
		return codSIA;
	}

	/**
	 * @param codSIA the codSIA to set
	 */
	public void setCodSIA(String codSIA) {
		this.codSIA = codSIA;
	}

	/**
	 * @return the estadoSIA
	 */
	public String getEstadoSIA() {
		return estadoSIA;
	}

	/**
	 * @param estadoSIA the estadoSIA to set
	 */
	public void setEstadoSIA(String estadoSIA) {
		this.estadoSIA = estadoSIA;
	}
}
