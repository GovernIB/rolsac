package es.caib.rolsac.api.v2.query;

public class Restriction {

    public static enum LOGIC {AND, OR};
    public static enum OPERATION {EQ, NEQ, IN, LIKE, LT, GT, LE, GE, EQ_DATE, NULL, NOT_NULL};

    private LOGIC logic;
    private String parameter;
    private OPERATION operation;
    private Object value;

    public Restriction(LOGIC logic, String parameter, OPERATION operation, Object value) {
        this.logic = logic;
        this.parameter = parameter;
        this.operation = operation;
        this.value = value;
    }
    
    public Restriction(LOGIC logic, String parameter, OPERATION operation) {
        this(logic, parameter, operation, null);
    }
    
    public Restriction(String parameter, OPERATION operation, Object value) {
        this(LOGIC.AND, parameter, operation, value);
    }
    
    public Restriction(String parameter, OPERATION operation) {
        this(LOGIC.AND, parameter, operation);
    }
    
    public LOGIC getLogic() {
        return logic;
    }

    public void setLogic(LOGIC logic) {
        this.logic = logic;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public OPERATION getOperation() {
        return operation;
    }

    public void setOperation(OPERATION operation) {
        this.operation = operation;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
