package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ProcedimentByPublicoObjetivoCriteria extends ByLongCriteria {

    public ProcedimentByPublicoObjetivoCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND p IN (    // p = this.field 
     *     SELECT po_.procedimientosLocales.elements
     *     FROM PublicoObjetivo AS po_ 
     *     WHERE po_.id IN (a1, a2, ...) 
     * )
     * 
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {
        StringBuilder query = new StringBuilder(" SELECT po_.procedimientosLocales.elements ")
                .append("FROM PublicoObjetivo AS po_ ")
                .append("WHERE po_.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
    }

}
