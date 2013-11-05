package es.caib.rolsac.api.v2.general.co;

import es.caib.rolsac.api.v2.query.QueryBuilder;

public class BasicByIniciCriteria implements CriteriaObject {

    private int iniciValue;

    public void parseCriteria(String criteria) throws CriteriaObjectParseException {
        try {
            iniciValue = Integer.parseInt(criteria);
        } catch (NumberFormatException e) {
            throw new CriteriaObjectParseException(e.getMessage(), e.getCause());
        }
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.setFirstResult(iniciValue);
    }
    
    public int getIniciValue()
    {
    	return this.iniciValue;
    }

}
