package org.ibit.rol.sac.model.ws;

/**
 * Clase que indica el resultado.
 * @author slromero
 *
 */
public class SiaResultado {
	/** Resultado nulo. **/
	public static int RESULTADO_NULO = -2;
	/** Resultado ok. **/
	public static int RESULTADO_OK = 1;
	/** Resultado error. **/
	public static int RESULTADO_ERROR = -1;
	/** resultado. **/
	public int resultado = RESULTADO_ERROR; 
	/** Mensaje. **/
	public String mensaje;
	/** Correctos. **/
	public int correctos = 0;
	/** Incorrectos. **/
	public int incorrectos = 0;
	/** Código SIA. **/
	public String codSIA;
	/** Estado SIA. **/
	public String estadoSIA;
	/** Operacion. **/
	public String operacion;
	
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

	public boolean isNulo() {
		if (resultado == SiaResultado.RESULTADO_NULO) {
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
}
