package es.caib.rolsac.api.v2.publicObjectiu.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class PublicObjectiuByFichaCriteria extends ByLongCriteria {

    public PublicObjectiuByFichaCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND po IN (    // po = this.field 
     *     SELECT f_.publicosObjetivo.elements
     *     FROM Ficha AS f_ 
     *     WHERE f_.id IN (a1, a2, ...) 
     * )
     * 
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {
        StringBuilder query = new StringBuilder(" SELECT f_.publicosObjetivo.elements ")
                .append("FROM Ficha AS f_ ")
                .append("WHERE f_.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
    }

}
