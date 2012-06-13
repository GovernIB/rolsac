package es.caib.rolsac.api.v2.general.co;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.caib.rolsac.api.v2.query.Order;
import es.caib.rolsac.api.v2.query.Order.DIRECTION;
import es.caib.rolsac.api.v2.query.QueryBuilder;

public class BasicByOrdenacioCriteria implements CriteriaObject {

    private List<Order> orders = new ArrayList<Order>();
    private String entityAlias;
    private String i18nAlias;
    
    public BasicByOrdenacioCriteria(String entityAlias, String i18nAlias) {
        this.entityAlias = entityAlias;
        this.i18nAlias = i18nAlias;
    }
    
    /**
     * Use case: "dataActualitzacio ASC, nom DESC, id". 
     * 
     * @param criteria
     * @throws CriteriaObjectParseException
     */
    public void parseCriteria(String criteria) throws CriteriaObjectParseException {
        try {
            List<String> ordres = Arrays.asList(criteria.split(","));
            String[] tokens;
            Order ordenacio;
            for (String ordre : ordres) {
                tokens = ordre.split(" ");
                ordenacio = new Order();
                if (tokens[0].contains(".")){
                    ordenacio.setField(tokens[0]);
                } else { 
                    if (tokens[0].startsWith(CriteriaObject.I18N_FIELD_PREFIX) && i18nAlias != null) {
                        ordenacio.setField(i18nAlias + "." + tokens[0].substring(CriteriaObject.I18N_FIELD_PREFIX.length()));
                    } else {
                        ordenacio.setField(entityAlias + "." + tokens[0]);
                    }
                }
                if (tokens.length > 1) {
                    ordenacio.setDirection(DIRECTION.valueOf(tokens[1].toUpperCase()));
                } else {
                    ordenacio.setDirection(DIRECTION.ASC);
                }
                orders.add(ordenacio);
            }
        } catch (Exception e) {
            throw new CriteriaObjectParseException(e.getMessage(), e.getCause());
        }
    }

    public void extendCriteria(QueryBuilder qb) {
        qb.addOrder(orders);
    }

}
