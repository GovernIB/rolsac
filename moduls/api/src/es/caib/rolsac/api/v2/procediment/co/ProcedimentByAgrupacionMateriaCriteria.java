package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ProcedimentByAgrupacionMateriaCriteria extends ByLongCriteria {

    public ProcedimentByAgrupacionMateriaCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND p IN (    // p = this.field
     *     SELECT mam.materia.procedimientosLocales.elements 
     *     FROM MateriaAgrupacionM AS mam 
     *     WHERE mam.agrupacion.id IN (a1, a2, ...) 
     * )
     * 
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {
        StringBuilder query = new StringBuilder(" SELECT mam_.materia.procedimientosLocales.elements ")
                .append("FROM MateriaAgrupacionM AS mam_ ")
                .append("WHERE mam_.agrupacion.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
    }

}
