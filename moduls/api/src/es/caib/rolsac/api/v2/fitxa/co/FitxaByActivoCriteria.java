package es.caib.rolsac.api.v2.fitxa.co;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.ibit.rol.sac.model.Validacion;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.PeriodoUtil;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.LOGIC;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class FitxaByActivoCriteria implements CriteriaObject {

	private String alias;
	private boolean activo;

	public FitxaByActivoCriteria(String alias) {
		this.alias = alias + ".";
	}

	public void parseCriteria(String criteria) {
		activo = BasicUtils.stringToBoolean(criteria);
	}

	public void extendCriteria(QueryBuilder qb) {
		if (activo) {
			addFitxaActivos(qb);
		} else {
			addFitxaCaducados(qb);
		}
	}

	private void addFitxaActivos(QueryBuilder qb) {

		qb.openParenthesis(LOGIC.AND);

		qb.addRestriction(new Restriction(alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));

		List<Restriction> restrictions = new ArrayList<Restriction>(2);
		restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.GE, PeriodoUtil.getNextDay()));
		restrictions.add(new Restriction(LOGIC.OR, alias + "fechaCaducidad", OPERATION.NULL));
		qb.addGroupedRestrictions(restrictions);

		restrictions = new ArrayList<Restriction>(2);
		restrictions.add(new Restriction(LOGIC.AND, alias + "fechaPublicacion", OPERATION.LE, PeriodoUtil.getToday()));
		restrictions.add(new Restriction(LOGIC.OR, alias + "fechaPublicacion", OPERATION.NULL));
		qb.addGroupedRestrictions(restrictions);

		qb.closeParenthesis();

	}

	private void addFitxaCaducados(QueryBuilder qb) {

		Date fecha = PeriodoUtil.getNextDay();

		qb.openParenthesis(LOGIC.AND);

		qb.addRestriction(new Restriction(alias + "validacion", OPERATION.NEQ, Validacion.PUBLICA));

		List<Restriction> restrictions = new ArrayList<Restriction>(2);
		restrictions.add(new Restriction(LOGIC.OR, alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));
		restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.LT, fecha));
		qb.addGroupedRestrictions(restrictions);

		restrictions = new ArrayList<Restriction>(3);
		restrictions.add(new Restriction(LOGIC.OR, alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));
		restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.NULL));
		restrictions.add(new Restriction(LOGIC.AND, alias + "fechaPublicacion", OPERATION.GT, fecha));
		qb.addGroupedRestrictions(restrictions);

		restrictions = new ArrayList<Restriction>(3);
		restrictions.add(new Restriction(LOGIC.OR, alias + "validacion", OPERATION.EQ, Validacion.PUBLICA));
		restrictions.add(new Restriction(LOGIC.AND, alias + "fechaCaducidad", OPERATION.GE, fecha));
		restrictions.add(new Restriction(LOGIC.AND, alias + "fechaPublicacion", OPERATION.GT, fecha));
		qb.addGroupedRestrictions(restrictions);

		qb.closeParenthesis();

	}

}