package es.caib.rolsac.apirest.v1.exception;

/**
 * Excepciones de la aplicacion
 * 
 * @author Indra
 *
 */
public class ExcepcionAplicacion extends Exception {

	Integer status;
	String mensajeError;	
	/**
	 *  Constructor
	 * @param status código de status a retornar
	 * @param mensajeError mensaje de error a retornar
	 */
	public ExcepcionAplicacion(int status, String mensajeError) {
		super(mensajeError);
		this.status = status;
		this.mensajeError = mensajeError;
	}

	public ExcepcionAplicacion() { }

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMensajeError() {
		return mensajeError;
	}

	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
					
}
