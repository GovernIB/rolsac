package es.caib.rolsac.api.v2.query;

public class QueryBuilderException extends Exception {

    private static final long serialVersionUID = -367082852573768115L;

    public QueryBuilderException() {
        super();
    }

    public QueryBuilderException(String message) {
        super(message);
    }

    public QueryBuilderException(Throwable cause) {
        super(cause);
    }

    public QueryBuilderException(String message, Throwable cause) {
        super(message, cause);
    }

}
