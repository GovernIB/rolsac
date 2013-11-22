package es.caib.rolsac.api.v2.procediment;

import java.util.List;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.procediment.co.ProcedimentByActivoCriteria;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;

public class ProcedimentUtils {

	public static void parseActiu(List<CriteriaObject> criteris, ProcedimentCriteria pc, String alias, QueryBuilder qb) {

		if ( pc.getActiu() != null ) {

			ProcedimentByActivoCriteria c = new ProcedimentByActivoCriteria(alias);
			c.parseCriteria( BasicUtils.booleanToString( pc.getActiu() ) );
			c.extendCriteria(qb);
			criteris.add(c);
			pc.setActiu(null);

		}

	}
	
	public static void parseActiu(List<CriteriaObject> criteris, ProcedimentCriteria pc, String alias) {
		if (pc.getActiu() != null) {
			ProcedimentByActivoCriteria c = new ProcedimentByActivoCriteria(alias);
			c.parseCriteria(BasicUtils.booleanToString(pc.getActiu()));
			criteris.add(c);
			pc.setActiu(null); // Para que no lo parsee BasicUtils.
		}
	}

	/** @deprecated*/
	public static boolean parseVisible(ProcedimentCriteria pc) {
		// Comprobamos si solicitan registros visibles.
		// Si el campo no se especifica, mostramos sólo visibles por defecto.
		boolean soloRegistrosVisibles = ( pc.getVisible() == null ) || ( pc.getVisible() != null && pc.getVisible().booleanValue() );
		// Ponemos campo a null para que no se procese como Criteria para la consulta HQL (i.e. para que no lo parsee BasicUtils.parseCriterias()).
		pc.setVisible(null);
		return soloRegistrosVisibles;
	}

	public static Integer parseEstadoUA(ProcedimentCriteria pc) {
		if (pc.getEstadoUA() != null) {
			Integer estadoUA = Integer.parseInt(pc.getEstadoUA());
			pc.setEstadoUA(null);
			return estadoUA;	// Para que no lo parsee BasicUtils.
		}
		return null;
	}

	private static String parseTelematico(ProcedimentCriteria pc, String where) {
		if (pc.getTelematico() != null && pc.getTelematico()) {
			if (where.equals(""))
				where += "WHERE ";
			else
				where += "AND ";

			where += "(t.idTraTel IS NOT NULL OR t.urlExterna IS NOT NULL) ";
		}
		pc.setTelematico(null);
		return where;
	}

	private static String parseVigente(ProcedimentCriteria pc, String where) {
		if (pc.getVigente() != null && pc.getVigente()) {
			if (where.equals(""))
				where += "WHERE ";
			else
				where += "AND ";

			where += "t.fase = 1 AND (t.dataInici > current_date OR t.dataInici IS NULL) AND (t.dataTancament < current_date OR t.dataTancament IS NULL) ";
		}
		pc.setVigente(null);
		return where;
	}

	public static Restriction ParseTelematicoVigente(ProcedimentCriteria pc, String alias) {
		String where = "";
		where = parseTelematico(pc, where);
		where = parseVigente(pc, where);
		if (where.equals(""))
			return null;

		return new Restriction(alias.concat(".id"), OPERATION.IN_SELECT, "SELECT t.procedimiento FROM Tramite AS t ".concat(where));
	}

}
