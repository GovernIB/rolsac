package es.caib.rolsac.api.v2.general.co;

import es.caib.rolsac.api.v2.query.QueryBuilder;

public interface CriteriaObject {

    public static final String I18N_FIELD_PREFIX = "t_";
    
    void parseCriteria(String criteria) throws CriteriaObjectParseException;

    void extendCriteria(QueryBuilder qb);

}
