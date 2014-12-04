package es.caib.rolsac.api.v2.fitxa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;

/**
 * Created by tcerda on 04/12/2014.
 */
public class FitxaByHechoVitalCriteria extends ByLongCriteria {

    public FitxaByHechoVitalCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND p IN (    // p = this.field
     *     SELECT hv.fichas.elements
     *     FROM HechoVital AS hv
     *     WHERE hvp.id IN (a1, a2, ...)
     * )
     *
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {

        StringBuilder query = new StringBuilder(" SELECT hv_.fichas.elements ")
                .append("FROM HechoVital AS hv_ ")
                .append("WHERE hv_.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(Restriction.LOGIC.AND, field, Restriction.OPERATION.IN_SELECT, query.toString()));
    }
}
