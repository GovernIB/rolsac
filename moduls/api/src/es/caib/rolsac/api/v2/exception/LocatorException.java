package es.caib.rolsac.api.v2.exception;

/**
 * Excepcion producida en la capa locator.
 */
public class LocatorException extends APIException {

    private static final long serialVersionUID = 1533788382944790337L;

    public LocatorException() {
        super();
    }

    /**
     * @param message
     */
    public LocatorException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public LocatorException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public LocatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
