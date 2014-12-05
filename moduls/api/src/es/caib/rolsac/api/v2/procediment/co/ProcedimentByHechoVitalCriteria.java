package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;

/**
 * Created by tcerda on 04/12/2014.
 */
public class ProcedimentByHechoVitalCriteria extends ByLongCriteria {

    public ProcedimentByHechoVitalCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND p IN (    // p = this.field
     *     SELECT hvp.procedimientosLocales.elements
     *     FROM HechoVitalProcedimiento AS hvp
     *     WHERE hvp.hechoVital.id IN (a1, a2, ...)
     * )
     *
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {

        StringBuilder query = new StringBuilder(" SELECT hvp_.procedimiento ")
                .append("FROM HechoVitalProcedimiento AS hvp_ ")
                .append("WHERE hvp_.hechoVital.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(Restriction.LOGIC.AND, field, Restriction.OPERATION.IN_SELECT, query.toString()));
    }
}
