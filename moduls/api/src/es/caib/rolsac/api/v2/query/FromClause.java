package es.caib.rolsac.api.v2.query;

public class FromClause {

    private String entityName;
    private String entityAlias;

    public FromClause() {
    }

    public FromClause(String entityName, String entityAlias) {
        super();
        this.entityName = entityName;
        this.entityAlias = entityAlias;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getEntityAlias() {
        return entityAlias;
    }

    public void setEntityAlias(String entityAlias) {
        this.entityAlias = entityAlias;
    }

}
