package es.caib.rolsac.api.v2.publicObjectiu.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class PublicObjectiuByServicioCriteria extends ByLongCriteria {

    public PublicObjectiuByServicioCriteria(String field) {
        super(field);
    }

    /**
     * Use case: ... AND po IN (    // po = this.field 
     *     SELECT p_.publicosObjetivo.elements
     *     FROM Servicio AS p_ 
     *     WHERE p_.id IN (a1, a2, ...) 
     * )
     * 
     * @param qb
     */
    @Override
    public void extendCriteria(QueryBuilder qb) {
        StringBuilder query = new StringBuilder(" SELECT p_.publicosObjetivo.elements ")
                .append("FROM Servicio AS p_ ")
                .append("WHERE p_.id ");
        CriteriaObjectUtil.buildInSelectQuery(query, this);
        qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
    }

}
