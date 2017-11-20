package es.caib.rolsac.back2.exception;

/**
 * Error cuando el texto introducido tiene algún caracter extraño.
 * @author slromero
 *
 */
public class TextNoValidException extends Exception {

	/** Serial version UID. **/
	private static final long serialVersionUID = 1L;

	/** Campo. **/
	private String campo = "";
	
	/**
	 * Constructor. 
	 * @param campo
	 */
	public TextNoValidException(final String campo) {
		super();
		this.setCampo(campo);
	}

	/**
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

}
