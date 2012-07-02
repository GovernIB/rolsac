package es.caib.rolsac.api.v2.exception;

/**
 * Excepcion producida en la capa adapter.
 */
public class InsertServiceException extends APIException {

    private static final long serialVersionUID = 8811700399183606652L;

    public InsertServiceException() {
        super();
    }

    /**
     * @param message
     */
    public InsertServiceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InsertServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public InsertServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
