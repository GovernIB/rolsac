package es.caib.rolsac.api.v2.query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import es.caib.rolsac.api.v2.general.PeriodoUtil;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;

public class QueryBuilder {

    private static Log log = LogFactory.getLog(QueryBuilder.class);

    private static final String HQL_TRADUCCIONES_FIELD = "traducciones";

    private String selectAlias;
    private boolean countFlag;
    private List<FromClause> fromClauses;
    private FromClause i18nFromClause;
    private String where;
    private String orderBy;
    private int maxResults;
    private int firstResult;
    private HashMap<String, Object> namedParameters;

    /**
     * Use case:
     * 
     * select distinct n
     * from ProcedimientoLocal as p left join p.normativas as n, n.traducciones as t
     * where p.id = 1234
     *     and n.numero = '>2'
     *     and index(t) = 'ca'
     *     and t.titulo like "%test%"
     * order by n.numero desc, n.id asc;
     * 
     * @param selectAlias n
     * @param fromClauses [{ProcedimientoLocal, p}, {p.normativas, n}]
     * @param i18nLang ca
     * @param i18nAlias t
     * 
     * @throws QueryBuilderException
     */
    public QueryBuilder(String selectAlias, List<FromClause> fromClauses, String i18nLang, String i18nAlias, boolean countFlag)
            throws QueryBuilderException {
        if (StringUtils.isBlank(selectAlias)) {
            throw new QueryBuilderException("'selectEntity' can not be null or empty.");
        }
        if (fromClauses == null || fromClauses.isEmpty()) {
            throw new QueryBuilderException("'fromClauses' can not be null or empty.");
        }
        if ((StringUtils.isBlank(i18nLang) && StringUtils.isNotBlank(i18nAlias))
                || (StringUtils.isNotBlank(i18nLang) && StringUtils.isBlank(i18nAlias))) {
            throw new QueryBuilderException("'i18nAlias' and 'i18nLang' must be both blank or both not blank.");
        }

        this.selectAlias = selectAlias;
        this.fromClauses = fromClauses;
        this.firstResult = 0;
        this.maxResults = 0;
        this.namedParameters = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(i18nAlias)) {
            String i18nEntity = selectAlias + "." + HQL_TRADUCCIONES_FIELD;
            this.i18nFromClause = new FromClause(i18nEntity, i18nAlias);
            this.where = "INDEX(" + i18nAlias + ") = :" + i18nLang;
            this.namedParameters.put(i18nLang, i18nLang);
        } else {
            this.where = "";
        }
        
        this.orderBy = "";
        this.countFlag = countFlag;
    }
    
    public QueryBuilder(String selectAlias, List<FromClause> fromClauses, String i18nLang, String i18nAlias)
            throws QueryBuilderException {
        this(selectAlias, fromClauses, i18nLang, i18nAlias, false);
    }

    public Query createQuery(Session session) throws HibernateException {
        Query query = session.createQuery(getHQL());
        prepareQuery(query);
        log.info(this.toString());
        return query;
    }

    private String getHQL() {
        StringBuilder hql = new StringBuilder("SELECT ");
        if (countFlag) {
            hql.append("COUNT(");
        }
        if (StringUtils.isNotBlank(orderBy)){
            hql.append(selectAlias);
        } else {
            hql.append("DISTINCT ").append(selectAlias);
        }
        if (countFlag) {
            hql.append(")");
        }

        hql.append(" FROM ");        
        Iterator<FromClause> fcIterator = fromClauses.iterator();
        while (fcIterator.hasNext()) {
            FromClause fc = fcIterator.next();
            hql.append(fc.getEntityName()).append(" AS ").append(fc.getEntityAlias());
            if (fcIterator.hasNext()) {
                hql.append(" LEFT JOIN ");
            }
        }

        if (i18nFromClause != null) {
            hql.append(", ").append(i18nFromClause.getEntityName()).append(" AS ")
                    .append(i18nFromClause.getEntityAlias());
        }

        if (StringUtils.isNotBlank(where)) {
            hql.append(" WHERE ").append(where);
        }
        if (StringUtils.isNotBlank(orderBy)) {
            hql.append(" ORDER BY ").append(orderBy);
        }

        return hql.toString();
    }

    private void addOperation(Restriction r) {
        String field = caseInsensitiveField(r.getParameter(), r.getValue());
        switch (r.getOperation()) {
        case EQ:
            where += field + " = :" + r.getParameter();
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case NEQ:
            where += field + " != :" + r.getParameter();
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case IN:
            where += field + " IN (:" + r.getParameter() + ")";
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case LIKE:
            where += field + " LIKE :" + r.getParameter();
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case LT:
            where += field + " < :" + r.getParameter();
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case GT:
            where += field + " > :" + r.getParameter();
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case LE:
            where += field + " <= :" + r.getParameter();
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case GE:
            where += field + " >= :" + r.getParameter();
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            break;
        case EQ_DATE:
            Date nextDay = PeriodoUtil.getNextDay((Date) r.getValue());
            where += "(" + field + " >= :" + r.getParameter() + " AND " + field + " < :" + r.getParameter() + "_)";
            namedParameters.put(r.getParameter(), caseInsensitiveValue(r.getValue()));
            namedParameters.put(r.getParameter() + "_", nextDay);
            break;
        case NULL:
            where += field + " IS NULL";
            break;
        case NOT_NULL:
            where += field + " IS NOT NULL";
            break;
        }
    }
    
    public void addRestriction(Restriction r) {
        if (logicMustBeAdded()) {where += " " + r.getLogic() + " ";}
        addOperation(r);
    }

    public void addGroupedRestrictions(Collection<Restriction> restrictions) {
        addGroupedRestrictions(restrictions.toArray(new Restriction[0]));
    }
    
    public void addGroupedRestrictions(Restriction... restrictions) {
        for (int i = 0; i < restrictions.length; i++) {
            if (i == 0) {
                if (logicMustBeAdded()) {where += " " + restrictions[i].getLogic() + " ";}
                where += "(";
            } else {
                where += " " + restrictions[i].getLogic() + " ";
            }
            addOperation(restrictions[i]);
        }
        if (restrictions.length > 0) {where += ")";}
    }

    public void openParenthesis(LOGIC logic) {
        if (logicMustBeAdded()) {where += " " + logic + " ";}
        where += "(";
    }
    
    public void closeParenthesis() {
        if (StringUtils.isNotBlank(where)) {where += ")";}
    }
    
    private boolean logicMustBeAdded() {
        return StringUtils.isNotBlank(where) && !where.trim().endsWith("(");
    }
    
    public void addOrder(Collection<Order> orders) {
        addOrder(orders.toArray(new Order[0]));
    }
    
    public void addOrder(Order... orders) {
        for (Order o: orders) {
            if (StringUtils.isNotBlank(orderBy)) {orderBy += ", ";}
            orderBy += o.getField() + " " + o.getDirection();
        }        
    }

    public void extendCriteriaObject(CriteriaObject co) {
        co.extendCriteria(this);
    }

    public void extendCriteriaObjects(Collection<CriteriaObject> cos) {
        for (CriteriaObject co : cos) {
            extendCriteriaObject(co);
        }
    }

    @SuppressWarnings("rawtypes")
    private void prepareQuery(Query query) throws HibernateException {
        for (String name : namedParameters.keySet()) {
            Object value = namedParameters.get(name);
            if (value instanceof Collection) {
                query.setParameterList(name, (Collection) value);
            } else {
                query.setParameter(name, value);
            }
        }
        if (maxResults > 0) {
            query.setMaxResults(maxResults);
        }
        if (firstResult > 0) {
            query.setFirstResult(firstResult);
        }
    }

    @SuppressWarnings("rawtypes")
    private String caseInsensitiveField(String field, Object value) {
        if (value instanceof String) {
            return "upper(" + field + ")";
        }

        if (value instanceof Collection) {
            Iterator it = ((Collection) value).iterator();
            if (it.hasNext() && it.next() instanceof String) {
                return "upper(" + field + ")";
            }
        }

        return field;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Object caseInsensitiveValue(Object value) {
        if (value instanceof String) {
            return ((String) value).toUpperCase();
        }

        if (value instanceof Collection) {
            Collection c = ((Collection) value);
            Collection caseInsensitiveCollection = new ArrayList(c.size());
            for (Object o : c) {
                if (o instanceof String) {
                    caseInsensitiveCollection.add(((String) o).toUpperCase());
                } else {
                    caseInsensitiveCollection.add(o);
                }
            }
            return caseInsensitiveCollection;
        }

        return value;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public void setFirstResult(int firstResult) {
        this.firstResult = firstResult;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("HQL: ").append(getHQL()).append("\nParameters:\n");
        for (String key : namedParameters.keySet()) {
            sb.append("\tkey: " + key + ", value: " + namedParameters.get(key) + "\n");
        }
        return sb.toString();
    }

}
