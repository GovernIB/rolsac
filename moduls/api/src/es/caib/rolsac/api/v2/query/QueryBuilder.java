package es.caib.rolsac.api.v2.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ibit.rol.sac.model.Periodo;

import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import net.sf.hibernate.HibernateException;
import net.sf.hibernate.Query;
import net.sf.hibernate.Session;

public class QueryBuilder {

	private static Log log = LogFactory.getLog(QueryBuilder.class);

	private static final String HQL_TRADUCCIONES_FIELD = "traducciones";

	private final String selectAlias;
	private final boolean countFlag;
	private final List<FromClause> fromClauses;
	private FromClause i18nFromClause;
	private String where;
	private String orderBy;
	private int maxResults;
	private int firstResult;
	private final HashMap<String, Object> namedParameters;
	private boolean distinct = true;

	/**
	 * Use case:
	 *
	 * select distinct n from ProcedimientoLocal as p left join p.normativas as n,
	 * n.traducciones as t where p.id = 1234 and n.numero = '>2' and index(t) = 'ca'
	 * and t.titulo like "%test%" order by n.numero desc, n.id asc;
	 *
	 * @param selectAlias
	 *            n
	 * @param fromClauses
	 *            [{ProcedimientoLocal, p}, {p.normativas, n}]
	 * @param i18nLang
	 *            ca
	 * @param i18nAlias
	 *            t
	 *
	 * @throws QueryBuilderException
	 */
	public QueryBuilder(final String selectAlias, final List<FromClause> fromClauses, final String i18nLang,
			final String i18nAlias, final boolean countFlag) throws QueryBuilderException {

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
		if (i18nLang != null && i18nLang.length() != 2) {
			throw new QueryBuilderException("'i18nLang' esta introduciendo un valor no valido: " + i18nLang);
		}

		this.selectAlias = selectAlias;
		this.fromClauses = fromClauses;
		this.firstResult = 0;
		this.maxResults = 0;
		this.namedParameters = new HashMap<String, Object>();

		if (StringUtils.isNotBlank(i18nAlias)) {
			final String i18nEntity = selectAlias + "." + HQL_TRADUCCIONES_FIELD;
			this.i18nFromClause = new FromClause(i18nEntity, i18nAlias);
			this.where = "INDEX(" + i18nAlias + ") = :" + i18nLang;
			this.namedParameters.put(i18nLang, i18nLang);
		} else {
			this.where = "";
		}

		this.orderBy = "";
		this.countFlag = countFlag;
	}

	public QueryBuilder(final String selectAlias, final List<FromClause> fromClauses, final String i18nLang,
			final String i18nAlias) throws QueryBuilderException {
		this(selectAlias, fromClauses, i18nLang, i18nAlias, false);
	}

	public Query createQuery(final Session session) throws HibernateException {
		final Query query = session.createQuery(getHQL(true));
		prepareQuery(query);
		log.debug(this.toString());
		return query;
	}

	public Query createQuery(final Session session, final boolean distinct) throws HibernateException {
		this.distinct = distinct;
		final Query query = session.createQuery(getHQL(distinct));
		prepareQuery(query);
		log.debug(this.toString());
		return query;
	}

	private String getHQL(boolean puedoHacerDistinct) {
		final StringBuilder hql = new StringBuilder("SELECT ");
		if (countFlag) {
			hql.append("COUNT(");
		}

		// Si no hay joins, podemos hacer un distinct de forma segura
		// Si no hay orderBy, tambi�n podemos hacer el distinct de forma segura
		if (fromClauses.size() > 1 && StringUtils.isNotBlank(orderBy)) {
			// Si hay orderBy y joins, hay que evaluar los orderBy para ver si podemos
			// eliminar las tablas con el distinct
			final List<String> ordres = Arrays.asList(orderBy.split(","));
			String[] tokens;
			for (final String ordre : ordres) {
				tokens = ordre.split(" ");
				if (tokens[0].contains(".")) {
					final String[] fieldParts = tokens[0].split("\\.");
					if (!fieldParts[0].equals(selectAlias)) {
						// Estamos ordenando por un campo en una tabla diferente
						puedoHacerDistinct = false;
					}
				}
			}

		}

		if (puedoHacerDistinct) {
			hql.append("DISTINCT ");

		}

		hql.append(selectAlias);
		if (countFlag) {
			hql.append(")");
		}

		hql.append(" FROM ");
		final Iterator<FromClause> fcIterator = fromClauses.iterator();
		while (fcIterator.hasNext()) {
			final FromClause fc = fcIterator.next();
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

	private void addOperation(final Restriction r) {
		final String field = caseInsensitiveField(r.getParameter(), r.getValue());
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

		case IN_SELECT: // IN_SELECT is case sensitive.
			where += r.getParameter() + " IN (" + r.getValue() + ")";
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

		case DATE:
			final Periodo periodo = (Periodo) r.getValue();
			where += "(";
			if (periodo.getFechaInicio() != null) {
				where += field + " >= :" + r.getParameter();
				namedParameters.put(r.getParameter(), periodo.getFechaInicio());
			}
			if (periodo.getFechaFin() != null) {
				if (periodo.getFechaInicio() != null) {
					where += " AND ";
				}
				;
				where += field + " < :" + r.getParameter() + "_";
				namedParameters.put(r.getParameter() + "_", periodo.getFechaFin());
			}
			where += ")";
			break;

		case NULL:
			where += field + " IS NULL";
			break;

		case NOT_NULL:
			where += field + " IS NOT NULL";
			break;
		}
	}

	public void addRestriction(final Restriction r) {
		if (logicMustBeAdded()) {
			where += " " + r.getLogic() + " ";
		}
		addOperation(r);
	}

	public void addGroupedRestrictions(final Collection<Restriction> restrictions) {
		addGroupedRestrictions(restrictions.toArray(new Restriction[0]));
	}

	public void addGroupedRestrictions(final Restriction... restrictions) {
		for (int i = 0; i < restrictions.length; i++) {
			if (i == 0) {
				if (logicMustBeAdded()) {
					where += " " + restrictions[i].getLogic() + " ";
				}
				where += "(";
			} else {
				where += " " + restrictions[i].getLogic() + " ";
			}
			addOperation(restrictions[i]);
		}
		if (restrictions.length > 0) {
			where += ")";
		}
	}

	public void openParenthesis(final LOGIC logic) {
		if (logicMustBeAdded()) {
			where += " " + logic + " ";
		}
		where += "(";
	}

	public void closeParenthesis() {
		if (StringUtils.isNotBlank(where)) {
			where += ")";
		}
	}

	private boolean logicMustBeAdded() {
		return StringUtils.isNotBlank(where) && !where.trim().endsWith("(");
	}

	public void addOrder(final Collection<Order> orders) {
		addOrder(orders.toArray(new Order[0]));
	}

	public void addOrder(final Order... orders) {
		for (final Order o : orders) {
			if (StringUtils.isNotBlank(orderBy)) {
				orderBy += ", ";
			}
			orderBy += o.getField() + " " + o.getDirection();
		}
	}

	public void extendCriteriaObject(final CriteriaObject co) {
		co.extendCriteria(this);
	}

	public void extendCriteriaObjects(final Collection<CriteriaObject> cos) {
		for (final CriteriaObject co : cos) {
			extendCriteriaObject(co);
		}
	}

	@SuppressWarnings("rawtypes")
	private void prepareQuery(final Query query) throws HibernateException {
		for (final String name : namedParameters.keySet()) {
			final Object value = namedParameters.get(name);
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
	private String caseInsensitiveField(final String field, final Object value) {
		if (value instanceof String) {
			return "upper(" + field + ")";
		}

		if (value instanceof Collection) {
			final Iterator it = ((Collection) value).iterator();
			if (it.hasNext() && it.next() instanceof String) {
				return "upper(" + field + ")";
			}
		}

		return field;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object caseInsensitiveValue(final Object value) {
		if (value instanceof String) {
			return ((String) value).toUpperCase();
		}

		if (value instanceof Collection) {
			final Collection c = ((Collection) value);
			final Collection caseInsensitiveCollection = new ArrayList(c.size());
			for (final Object o : c) {
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

	public void setMaxResults(final int maxResults) {
		this.maxResults = maxResults;
	}

	public void setFirstResult(final int firstResult) {
		this.firstResult = firstResult;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("HQL: ").append(getHQL(this.distinct)).append("\nParameters:\n");
		for (final String key : namedParameters.keySet()) {
			sb.append("\tkey: " + key + ", value: " + namedParameters.get(key) + "\n");
		}
		return sb.toString();
	}

}
