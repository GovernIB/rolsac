package es.caib.rolsac.api.v2.procediment;

import java.util.List;

import es.caib.rolsac.api.v2.general.BasicUtils;
import es.caib.rolsac.api.v2.general.co.CriteriaObject;
import es.caib.rolsac.api.v2.procediment.co.ProcedimentByActivoCriteria;
import es.caib.rolsac.api.v2.query.QueryBuilder;

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
    
}
