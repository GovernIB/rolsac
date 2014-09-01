package es.caib.rolsac.api.v2.seccio.co;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class SeccioByArrelCriteria implements CriteriaObject {

    private String alias;
    private boolean arrel;

    public SeccioByArrelCriteria(String alias) {
        this.alias = alias + ".";
    }
    
    public void parseCriteria(String criteria) {
        arrel = BasicUtils.stringToBoolean(criteria);
    }
    
    public void extendCriteria(QueryBuilder qb) {
        if (arrel) {
            qb.addRestriction(new Restriction(LOGIC.AND, alias + "padre", OPERATION.NULL));
        } else {
            qb.addRestriction(new Restriction(LOGIC.AND, alias + "padre", OPERATION.NOT_NULL));
        }
    }
}
