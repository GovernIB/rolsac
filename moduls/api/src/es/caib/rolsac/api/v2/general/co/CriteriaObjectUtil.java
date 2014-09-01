package es.caib.rolsac.api.v2.general.co;

public class CriteriaObjectUtil {

    private CriteriaObjectUtil(){
    }
    
    public static void buildInSelectQuery(StringBuilder query, ByLongCriteria longCriteria) {
        if (longCriteria.value != null) {
            query.append(" = ").append(longCriteria.value);
        }
        if (longCriteria.minValue != null) {
            query.append(" >= ").append(longCriteria.minValue);
        }
        if (longCriteria.maxValue != null) {
            if (longCriteria.minValue != null) {
                query.append(" AND ");
            }
            query.append(" <= ").append(longCriteria.maxValue);
        }
        if (longCriteria.valuesSet != null) {
            query.append(" IN (");
            for (Long id : longCriteria.valuesSet) {
                query.append(id).append(",");
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") ");
        }
    }
    
}
