package es.caib.rolsac.api.v2.exception;

/**
 * Excepcion producida en la capa adapter.
 */
public class QueryServiceException extends APIException {

    private static final long serialVersionUID = -7734082264686358908L;

    public QueryServiceException() {
        super();
    }

    /**
     * @param message
     */
    public QueryServiceException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public QueryServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public QueryServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
