package es.caib.rolsac.api.v2.servicio.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ServicioByAgrupacionHechoVitalCriteria extends ByLongCriteria {

    public ServicioByAgrupacionHechoVitalCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND p IN (    // p = this.field
     *   SELECT hvproc.Servicio 
     *   FROM AgrupacionHechoVital AS ahv,
     *      ahv.hechosVitalesAgrupacionHV as hvahv,
     *      hvahv.hechoVital.hechosVitalesServicios AS hvproc
     *   WHERE ahv.id IN (1, 2, ...)
     * )
     * 
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {
        StringBuilder query = new StringBuilder(" SELECT hvproc_.Servicio ")
                .append("FROM AgrupacionHechoVital AS ahv_, ")
                .append("ahv_.hechosVitalesAgrupacionHV as hvahv_, ")
                .append("hvahv_.hechoVital.hechosVitalesServicios AS hvproc_ ")
                .append("WHERE ahv_.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
    }

}
