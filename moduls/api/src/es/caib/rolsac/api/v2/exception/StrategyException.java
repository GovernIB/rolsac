package es.caib.rolsac.api.v2.exception;

/**
 * Excepcion producida en la capa strategy.
 */
public class StrategyException extends APIException {

    private static final long serialVersionUID = -3724381511721132028L;

    public StrategyException() {
        super();
    }

    /**
     * @param message
     */
    public StrategyException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public StrategyException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public StrategyException(String message, Throwable cause) {
        super(message, cause);
    }

}
