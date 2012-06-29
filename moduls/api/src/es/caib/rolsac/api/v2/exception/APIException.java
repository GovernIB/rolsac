package es.caib.rolsac.api.v2.exception;

import javax.ejb.EJBException;

/**
 * Excepcion base de la API.
 */
public class APIException extends Exception {

    private static final long serialVersionUID = -5124052742796743582L;

    public APIException() {
    }

    /**
     * @param message
     */
    public APIException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public APIException(Throwable cause) {
        super(cause);
        setRootCause(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public APIException(String message, Throwable cause) {
        super(message, cause);
        setRootCause(cause);
    }

    protected void setRootCause(Throwable cause) {
        while (cause != null) {
            if (cause instanceof EJBException) {
                cause = ((EJBException) cause).getCausedByException();
            } else {
                cause = cause.getCause();
            }
        }
    }

}
