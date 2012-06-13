package es.caib.rolsac.api.v2.general.co;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public abstract class ByDateCriteria implements CriteriaObject {

    // "dd/MM/yyyy HH:mm:ss"
    public final static SimpleDateFormat DATE_CRITERIA_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

    private String field;
    private Date value;

    protected ByDateCriteria(String field) {
        this.field = field;
    }

    public void parseCriteria(String criteria) throws CriteriaObjectParseException {
        try {
            value = DATE_CRITERIA_FORMATTER.parse(criteria);
        } catch (ParseException e) {
            throw new CriteriaObjectParseException(e.getMessage(), e.getCause());
        }
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.addRestriction(new Restriction(field, OPERATION.EQ_DATE, value));
    }

}
