package es.caib.rolsac.api.v2.procediment.co;

import es.caib.rolsac.api.v2.general.co.ByLongCriteria;
import es.caib.rolsac.api.v2.general.co.CriteriaObjectUtil;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;

public class ProcedimentByPlataformaCriteria extends ByLongCriteria {

	public ProcedimentByPlataformaCriteria(final String field) {
		super(field);
	}

	/**
	 * Use case: ... AND p IN ( // p = this.field SELECT
	 * hvp.procedimientosLocales.elements FROM HechoVitalProcedimiento AS hvp WHERE
	 * hvp.hechoVital.id IN (a1, a2, ...) )
	 *
	 * @param qb
	 */
	@Override
	public void extendCriteria(final QueryBuilder qb) {

		final StringBuilder query = new StringBuilder(" SELECT tram_.procedimiento ").append("FROM Tramite AS tram_ ")
				.append("WHERE tram_.plataforma.id ");
		CriteriaObjectUtil.buildInSelectQuery(query, this);
		qb.addRestriction(
				new Restriction(Restriction.LOGIC.AND, field, Restriction.OPERATION.IN_SELECT, query.toString()));
	}

}
