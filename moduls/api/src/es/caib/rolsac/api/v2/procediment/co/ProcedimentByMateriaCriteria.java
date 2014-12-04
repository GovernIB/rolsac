package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;

/**
 * Created by tcerda on 04/12/2014.
 */
public class ProcedimentByMateriaCriteria extends ByLongCriteria {

    public ProcedimentByMateriaCriteria(String field) {
        super(field);
        }

    /**
     * Use case: ... AND p IN (    // p = this.field
     *     SELECT ma.materia.procedimientosLocales.elements
     *     FROM Materia AS ma
     *     WHERE ma.id IN (a1, a2, ...)
     * )
     *
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {

        StringBuilder query = new StringBuilder(" SELECT ma_.procedimientosLocales.elements ")
                .append("FROM Materia AS ma_ ")
                .append("WHERE ma_.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(Restriction.LOGIC.AND, field, Restriction.OPERATION.IN_SELECT, query.toString()));
    }

}
