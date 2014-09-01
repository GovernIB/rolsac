package es.caib.rolsac.api.v2.query;

public class Order {

    public static enum DIRECTION {ASC, DESC};
    
    private String field;
    private DIRECTION direction;

    public Order() {
    }

    public Order(String field, DIRECTION direction) {
        this.field = field;
        this.direction = direction;
    }
    
    public Order(String field) {
        this(field, DIRECTION.ASC);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public DIRECTION getDirection() {
        return direction;
    }

    public void setDirection(DIRECTION direction) {
        this.direction = direction;
    }

}