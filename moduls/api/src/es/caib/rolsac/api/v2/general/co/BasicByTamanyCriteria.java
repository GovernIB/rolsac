package es.caib.rolsac.api.v2.general.co;

import es.caib.rolsac.api.v2.query.QueryBuilder;

public class BasicByTamanyCriteria implements CriteriaObject {

    private int tamanyValue;

    public void parseCriteria(String criteria) throws CriteriaObjectParseException {
        try {
            tamanyValue = Integer.parseInt(criteria);
        } catch (NumberFormatException e) {
            throw new CriteriaObjectParseException(e.getMessage(), e.getCause());
        }
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.setMaxResults(tamanyValue);
    }

}
