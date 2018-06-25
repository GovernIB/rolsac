package es.caib.rolsac.api.v2.servicio;

import java.util.List;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.query.QueryBuilder;
import es.caib.rolsac.api.v2.query.Restriction;
import es.caib.rolsac.api.v2.query.Restriction.OPERATION;
import es.caib.rolsac.api.v2.servicio.co.ServicioByActivoCriteria;

public class ServicioUtils {

	public static void parseActiu(List<CriteriaObject> criteris, ServicioCriteria pc, String alias, QueryBuilder qb) {

		if ( pc.getActiu() != null ) {

			ServicioByActivoCriteria c = new ServicioByActivoCriteria(alias);
			c.parseCriteria( BasicUtils.booleanToString( pc.getActiu() ) );
			c.extendCriteria(qb);
			criteris.add(c);
			pc.setActiu(null);

		}

	}
	
	public static void parseActiu(List<CriteriaObject> criteris, ServicioCriteria pc, String alias) {
		if (pc.getActiu() != null) {
			ServicioByActivoCriteria c = new ServicioByActivoCriteria(alias);
			c.parseCriteria(BasicUtils.booleanToString(pc.getActiu()));
			criteris.add(c);
			pc.setActiu(null); // Para que no lo parsee BasicUtils.
		}
	}

	/*
	public static Integer parseEstadoUA(ServicioCriteria pc) {
		if (pc.getEstadoUA() != null) {
			Integer estadoUA = Integer.parseInt(pc.getEstadoUA());
			pc.setEstadoUA(null);
			return estadoUA;	// Para que no lo parsee BasicUtils.
		}
		return null;
	}
	*/

/*	private static String parseTelematico(ServicioCriteria pc, String where) {
		if (pc.getTelematico() != null && pc.getTelematico()) {
			if (where.equals(""))
				where += "WHERE ";
			else
				where += "AND ";

			where += "(t.idTraTel IS NOT NULL OR t.urlExterna IS NOT NULL) ";
		}
		pc.setTelematico(null);
		return where;
	}*/

	private static String parseVigente(ServicioCriteria pc, String where) {
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

	public static Restriction ParseVigente(ServicioCriteria pc, String alias) {
		String where = "";
		where = parseVigente(pc, where);
		if (where.equals(""))
			return null;

		return new Restriction(alias.concat(".id"), OPERATION.IN_SELECT, "SELECT t.procedimiento FROM Tramite AS t ".concat(where));
	}
	
	public static Restriction ParseTelematico(ServicioCriteria pc, String alias) {
		//si no se incluye los busca todos, si no busca los telematicos o los que no son telematicos
		if (pc.getTelematico() == null) {
			return null;
		}				
		int tele = pc.getTelematico()?1:0;
				
		return new Restriction(alias.concat(".telematico"), OPERATION.EQ, tele);
	}
	
	

}
