package es.caib.rolsac.api.v2.general.co;

import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public abstract class ByStringCriteria implements CriteriaObject {

    private String field;
    private String value;
    private boolean likeFlag;

    protected ByStringCriteria(String field) {
        this.field = field;
    }

    public void parseCriteria(String criteria) {
        value = criteria;
        likeFlag = value.contains("%");
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.addRestriction(new Restriction(field, likeFlag ? OPERATION.LIKE : OPERATION.EQ, value));
    }

}
