package es.caib.rolsac.api.v2.exception;

/**
 * Excepcion producida en la capa delegate.
 */
public class DelegateException extends APIException {

    private static final long serialVersionUID = 8554672202793062435L;

    public DelegateException() {
        super();
    }

    /**
     * @param message
     */
    public DelegateException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public DelegateException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public DelegateException(String message, Throwable cause) {
        super(message, cause);
    }
    
}