package es.caib.rolsac.api.v2.general.co;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public abstract class ByLongCriteria implements CriteriaObject {

    private String field;
    private Long value;
    private Long minValue;
    private Long maxValue;
    private Set<Long> valuesSet;

    protected ByLongCriteria(String field) {
        this.field = field;
    }

    /**
     * Use cases: "0", "<2", ">=5", "1-3", "1,3,6"
     * 
     * @param criteria
     * @throws CriteriaObjectParseException
     */
    public void parseCriteria(String criteria) throws CriteriaObjectParseException {
        boolean parsed = false;
        try {
            if (criteria.startsWith("<=")) {
                maxValue = Long.parseLong(criteria.substring(1)) + 1;
                parsed = true;
            }

            if (!parsed && criteria.startsWith(">=")) {
                minValue = Long.parseLong(criteria.substring(1)) - 1;
                parsed = true;
            }

            if (!parsed && criteria.startsWith("<")) {
                maxValue = Long.parseLong(criteria.substring(1));
                parsed = true;
            }

            if (!parsed && criteria.startsWith(">")) {
                minValue = Long.parseLong(criteria.substring(1));
                parsed = true;
            }

            if (!parsed && !criteria.startsWith("-") && criteria.contains("-")) {
                String[] range = criteria.split("-");
                minValue = Long.parseLong(range[0]) - 1;
                maxValue = Long.parseLong(range[1]) + 1;
                parsed = true;
            }

            if (!parsed && criteria.contains(",")) {
                List<String> values = Arrays.asList(criteria.split(","));
                valuesSet = new HashSet<Long>();
                for (String v : values) {
                    valuesSet.add(Long.parseLong(v));
                }
                parsed = true;
            }

            if (!parsed) {
                value = Long.parseLong(criteria);
            }
        } catch (Exception e) {
            throw new CriteriaObjectParseException(e.getMessage(), e.getCause());
        }
    }

    public void extendCriteria(QueryBuilder qb) {
        if (value != null) {
            qb.addRestriction(new Restriction(field, OPERATION.EQ, value));
        }
        if (minValue != null) {
            qb.addRestriction(new Restriction(field, OPERATION.GT, value));
        }
        if (maxValue != null) {
            qb.addRestriction(new Restriction(field, OPERATION.LT, value));
        }
        if (valuesSet != null) {
            qb.addRestriction(new Restriction(field, OPERATION.IN, valuesSet));
        }
    }

}
