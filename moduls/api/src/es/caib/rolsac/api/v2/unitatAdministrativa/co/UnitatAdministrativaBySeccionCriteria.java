package es.caib.rolsac.api.v2.unitatAdministrativa.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class UnitatAdministrativaBySeccionCriteria extends ByLongCriteria {

    public UnitatAdministrativaBySeccionCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND ua IN (    // ua = this.field
     *     SELECT fua_.elements.unidadAdministrativa
     *     FROM FichaUA AS fua_
     *     WHERE fua_.seccion.id IN (a1, a2, ...)
     * )
     *
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {
        StringBuilder query = new StringBuilder(" SELECT fua_.unidadAdministrativa ")
                .append("FROM FichaUA AS fua_ ")
                .append("WHERE fua_.seccion.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
    }

}
