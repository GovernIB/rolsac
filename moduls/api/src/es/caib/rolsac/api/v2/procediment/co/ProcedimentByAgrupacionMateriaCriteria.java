package es.caib.rolsac.api.v2.procediment.co;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectParseException;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ProcedimentByAgrupacionMateriaCriteria implements CriteriaObject {

    private String field;
    Set<Long> agrupacionMateriaIDs;

    public ProcedimentByAgrupacionMateriaCriteria(String field) {
        this.field = field;
    }

    public void parseCriteria(String criteria) throws CriteriaObjectParseException {
        agrupacionMateriaIDs = new HashSet<Long>();
        try {
            if (criteria.contains(",")) {
                criteria = criteria.replaceAll(",$", ""); // remove possible trailing comma
                List<String> values = Arrays.asList(criteria.split(","));
                for (String v : values) {
                    agrupacionMateriaIDs.add(Long.parseLong(v.trim()));
                }
            } else {
                agrupacionMateriaIDs.add(Long.parseLong(criteria.trim()));
            }
        } catch (Exception e) {
            throw new CriteriaObjectParseException(e.getMessage(), e.getCause());
        }
    }

    /**
     * Use case: ... AND p IN (    // p = this.field
     *    SELECT mam.materia.procedimientosLocales.elements 
     *    FROM MateriaAgrupacionM AS mam 
     *    WHERE mam.agrupacion.id IN (a1, a2, ...) 
     * )
     * 
     * @param qb
     */
    public void extendCriteria(QueryBuilder qb) {
        if (agrupacionMateriaIDs != null && !agrupacionMateriaIDs.isEmpty()) {

            StringBuilder query = new StringBuilder(" SELECT mam_.materia.procedimientosLocales.elements ")
                    .append("FROM MateriaAgrupacionM AS mam_ ")
                    .append("WHERE mam_.agrupacion.id IN (");
            
            for (Long id : agrupacionMateriaIDs) {
                query.append(id).append(",");
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") ");
            
            qb.addRestriction(new Restriction(LOGIC.AND, field, OPERATION.IN_SELECT, query.toString()));
        }
    }

}
