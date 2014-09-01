package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ProcedimentByAgrupacionHechoVitalCriteria extends ByLongCriteria {

    public ProcedimentByAgrupacionHechoVitalCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND p IN (    // p = this.field
     *   SELECT hvproc.procedimiento 
     *   FROM AgrupacionHechoVital AS ahv,
     *      ahv.hechosVitalesAgrupacionHV as hvahv,
     *      hvahv.hechoVital.hechosVitalesProcedimientos AS hvproc
     *   WHERE ahv.id IN (1, 2, ...)
     * )
     * 
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {
        StringBuilder query = new StringBuilder(" SELECT hvproc_.procedimiento ")
                .append("FROM AgrupacionHechoVital AS ahv_, ")
                .append("ahv_.hechosVitalesAgrupacionHV as hvahv_, ")
                .append("hvahv_.hechoVital.hechosVitalesProcedimientos AS hvproc_ ")
                .append("WHERE ahv_.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
    }

}
